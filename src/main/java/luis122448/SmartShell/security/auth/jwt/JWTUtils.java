package luis122448.SmartShell.security.auth.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.*;
import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.auth.user.UserDetailsCustom;
import luis122448.SmartShell.security.auth.user.UserDetailsServiceCustom;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.security.utility.constant.SecurityConstant;

@Slf4j
@Component
public class JWTUtils {

	private final UserDetailsServiceCustom userDetailsServiceCustom;

	public JWTUtils(UserDetailsServiceCustom userDetailsServiceCustom) {
		this.userDetailsServiceCustom = userDetailsServiceCustom;
	}

	public String generateJwtToken(String company, String usuario, Boolean swRefreshToken) throws SecurityException, GenericAuthServiceException {
		try {
			UserDetailsCustom userDetailsCustom = userDetailsServiceCustom.loadUserByUsernameAndCompany(company, usuario);
			long time= (!swRefreshToken)? SecurityConstant.TOKEN_EXPIRATION_TIME_TOKEN:SecurityConstant.TOKEN_EXPIRATION_TIME_REFRESH_TOKEN;
			Map<String, Object> tokenData = new HashMap<>();
			tokenData.put(SecurityConstant.AUTHORITIES, userDetailsCustom.getAuthorities());
			tokenData.put(SecurityConstant.IDCOMPANY, userDetailsCustom.getIdcompany());
			tokenData.put(SecurityConstant.COMPANY, userDetailsCustom.getCompany());
			tokenData.put(SecurityConstant.APPELLATION, userDetailsCustom.getAppellation());
			tokenData.put(SecurityConstant.CODUSER, userDetailsCustom.getCoduser());
			tokenData.put(SecurityConstant.USERNAME, userDetailsCustom.getUsername());
            return Jwts
					.builder()
					.setSubject(userDetailsCustom.getCoduser())
					.setIssuedAt(new Date()).setIssuer(SecurityConstant.ISSUER_INFO)
					.setExpiration(new Date(System.currentTimeMillis() + time))
					.signWith(SignatureAlgorithm.HS512, SecurityConstant.SUPER_SECRET_KEY)
					.addClaims(tokenData)
					.compact();
		} catch (SecurityException e) {
			throw new SecurityException("ERROR IN GENERATE TOKEN", e);
		}
	}
	
	public String generateJwtFromTokenRefresh(String refreshToken) throws SecurityException {
		try{
			Map<String, Object> tokenData = getDataJwtToken(refreshToken);
			String coduser = tokenData.get(SecurityConstant.CODUSER).toString();
            return Jwts
					.builder()
					.setSubject(coduser)
					.setIssuedAt(new Date()).setIssuer(SecurityConstant.ISSUER_INFO)
					.setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.TOKEN_EXPIRATION_TIME_TOKEN +  SecurityConstant.TOKEN_EXPIRATION_TIME_REFRESH_TOKEN))
					.signWith(SignatureAlgorithm.HS512, SecurityConstant.SUPER_SECRET_KEY)
					.addClaims(tokenData)
					.compact();
		} catch (SecurityException e) {
			throw new SecurityException("\"ERROR IN GENERATE REFRESH TOKEN", e);
		}
	}

	public Map<String, Object> getDataJwtToken(String token) throws SecurityException {
		try{
			Claims claims = Jwts.parser().setSigningKey(SecurityConstant.SUPER_SECRET_KEY).parseClaimsJws(token).getBody();
			return new HashMap<>(claims);
		} catch (SignatureException e) {
			throw new SecurityException("INVALID LOGIN, PLEASE RE-LOGIN", e);
		}
	}

	public boolean validateJwtToken(String authToken) throws SecurityException  {
		try {
			Jwts.parser().setSigningKey(SecurityConstant.SUPER_SECRET_KEY).parseClaimsJws(authToken);
			return true;
		} catch (
		ExpiredJwtException e) {
			throw new SecurityException("TOKEN EXPIRED", e);
		} catch (
		UnsupportedJwtException e) {
			throw new SecurityException("UNSUPPORTED TOKEN", e);
		} catch (SignatureException e) {
			throw new SecurityException("TOKEN INVALID, NEED RE-LOGIN",e);
		} catch (
		JwtException e) {
			throw new SecurityException("INVALID TOKEN", e);
		}
	}
	
	public String parseJwt(HttpServletRequest request) throws SecurityException {
		String headerAuth = request.getHeader(SecurityConstant.HEADER_AUTHORIZACION_KEY);
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(SecurityConstant.TOKEN_BEARER_PREFIX)) {
			return headerAuth.substring(7);
		}
		return null;		
	}
}
