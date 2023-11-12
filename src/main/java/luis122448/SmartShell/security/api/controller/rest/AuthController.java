package luis122448.SmartShell.security.api.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
import luis122448.SmartShell.security.api.dto.LoginRequest;
import luis122448.SmartShell.security.api.dto.RefreshToken;
import luis122448.SmartShell.security.api.dto.VerifyCodeRequest;
import luis122448.SmartShell.application.domain.persistence.entity.UsuarioEntity;
import luis122448.SmartShell.security.api.service.impl.VerifyCode;
import luis122448.SmartShell.security.api.service.service.UsuarioService;
import luis122448.SmartShell.security.auth.jwt.JWTUtils;
import luis122448.SmartShell.security.api.utility.ApiResponseVerifyCode;
import luis122448.SmartShell.security.api.utility.ApiResponseToken;
import static java.util.Objects.isNull;

import java.util.List;
import java.util.stream.Collectors;
import static luis122448.SmartShell.security.constant.SecurityConstant.API_AUTH;

//@RequiredArgsConstructor
@RestController
@RequestMapping(API_AUTH)
public class AuthController {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserDetailsService userDetailsService;
	private final VerifyCode verifyCode;
	private final JWTUtils jwtUtils;
	private final UsuarioService usuarioService;
	public AuthController(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService,
			VerifyCode verifyCode, JWTUtils jwtUtils, UsuarioService usuarioService) {
		super();
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userDetailsService = userDetailsService;
		this.verifyCode = verifyCode;
		this.jwtUtils = jwtUtils;
		this.usuarioService = usuarioService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(HttpServletRequest request,@RequestBody LoginRequest t){
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(t.getCoduser());
			if (isNull(userDetails)) {
				 ApiResponseVerifyCode obj = new ApiResponseVerifyCode((short) -2,"USUARIO NO EXISTE",null,null);
				 return ResponseEntity.ok(obj);
			} else {
				// Verificando el Password
				if (this.bCryptPasswordEncoder.matches(t.getPassword(), userDetails.getPassword())) {
					// Si todo es correcto, entonces ...
					UsuarioEntity tmp = new UsuarioEntity();
					tmp.setCoduser(t.getCoduser());
					UsuarioEntity user = this.usuarioService.findById(tmp).getObject().get();
					ApiResponseVerifyCode obj = this.verifyCode.createCode(user);
					return ResponseEntity.ok(obj);
				} else {
					ApiResponseVerifyCode obj = new ApiResponseVerifyCode((short) -2,"PASSWORD INCORRECTO",null,null);
					return ResponseEntity.ok(obj);
				}
			}
		} catch (Exception e) {
			ApiResponseVerifyCode obj = new ApiResponseVerifyCode((short) -2,e.getMessage(),null,null);
			return ResponseEntity.ok(obj);
		}
	}
	
	@PostMapping("/verify-code")
	public ResponseEntity<?> verifyCode(@RequestBody VerifyCodeRequest t){
		try {
			UsuarioEntity obj = this.verifyCode.verifyCode(t.getCoduser(), t.getCodver()); 
			if (isNull(obj)) {
				ApiResponseToken tok = new ApiResponseToken((short) -2,"CODIGO DE VERIFICACION NO VALIDO",t.getCoduser(),null,null,null);
				return ResponseEntity.ok(tok);
			} else {
				String token = this.jwtUtils.generateJwtToken(t.getCoduser(), false);
				String refreshToken =  this.jwtUtils.generateJwtToken(t.getCoduser(), true);
				String codrol = obj.getCodrol();
				ApiResponseToken tok = new ApiResponseToken((short) 1,"Ok",t.getCoduser(),codrol,token,refreshToken);
				return ResponseEntity.ok(tok);
			}
		} catch (Exception e) {
			ApiResponseToken tok = new ApiResponseToken((short) -2,e.getMessage(),t.getCoduser(),null,null,null);
			return ResponseEntity.ok(tok);
		}	
	}
	
	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshToken t){
		try {
			if (this.jwtUtils.validateJwtToken(t.getRefreshToken())) {
				String token = this.jwtUtils.generateJwtFromTokenRefresh(t.getRefreshToken());
				String user =  this.jwtUtils.getUserNameFromJwtToken(token);
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(user);
				// Lista de ROL
				List<String> authorities = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
						.collect(Collectors.toList());
				// Obteniendo solo el Primer Elemento
				String codrol = authorities.get(0);
				codrol =  codrol.substring(5, codrol.length()); // Extraer solo el nombre del ROL, sin prefix
				ApiResponseToken tok = new ApiResponseToken((short) 1,"Ok",userDetails.getUsername(),codrol,token,t.getRefreshToken());
				return ResponseEntity.ok(tok);
			} else {
				ApiResponseToken tok = new ApiResponseToken((short) 1,"LA SESION A EXPIRADO",null,null,null,null);
				return ResponseEntity.ok(tok);
			}
		} catch (Exception e) {
			ApiResponseToken tok = new ApiResponseToken((short) -2,e.getMessage(),null,null,null,null);
			return ResponseEntity.ok(tok);
		}
	}
	
}
