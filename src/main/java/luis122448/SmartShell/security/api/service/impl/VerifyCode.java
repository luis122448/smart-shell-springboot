package luis122448.SmartShell.security.api.service.impl;
//import org.springframework.context.ApplicationEventPublisher;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.UsuarioEntity;
import luis122448.SmartShell.security.api.service.service.AuthService;
import luis122448.SmartShell.security.api.service.service.UsuarioService;
import luis122448.SmartShell.security.api.utility.ApiResponseVerifyCode;
import luis122448.SmartShell.security.auth.utils.TOTPUtil;

@Slf4j
@Service
//@RequiredArgsConstructor
public class VerifyCode {

	private AuthService authService;
	private UsuarioService usuarioService;
//	private ApplicationEventPublisher applicationEventPublisher;
	private TOTPUtil tOTPUtil;
	
	
	public VerifyCode(AuthService authService, UsuarioService usuarioService, TOTPUtil tOTPUtil) {
	super();
	this.authService = authService;
	this.usuarioService = usuarioService;
	this.tOTPUtil = tOTPUtil;
}

	@Transactional
	public ApiResponseVerifyCode createCode(UsuarioEntity t) {
		try {
			// Generanco Codigo
			String codver = this.tOTPUtil.generateCode();
			log.info(codver);
			// Actualizando Codigo en BD, 30s de Duracion
			this.authService.updateVerifyCode(t.getCoduser(), codver);
			// Respuesta
			return new ApiResponseVerifyCode((short) 1, "Ok", t.getCoduser(), codver);
		} catch (Exception e) {
			return new ApiResponseVerifyCode((short) -2, e.getMessage(), t.getCoduser(), null);
		}
	}
	
	public UsuarioEntity verifyCode(String coduser, String codver) {
		try {
			UsuarioEntity tmp = new UsuarioEntity();
			tmp.setCoduser(coduser);
			UsuarioEntity obj =  this.usuarioService.findById(tmp).getObject().get();
			// Verifico que el Codigo Ingresado, le pertence al Usuario ...
			if (obj.getCodver().equals(codver) && this.tOTPUtil.verifyCode(codver)) {
				return obj; 
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
}

