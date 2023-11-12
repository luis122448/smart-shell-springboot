package luis122448.SmartShell.security.auth.jwt;

import java.util.Date;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.security.constant.SecurityConstant;

@Slf4j
@Component
public class JWTUtils {

	private final UserDetailsService userDetailsService;
	
	public JWTUtils(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}

	public String generateJwtToken(String usuario, Boolean swRefreshToken) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(usuario);
			
		Long time= (!swRefreshToken)? SecurityConstant.TOKEN_EXPIRATION_TIME_TOKEN:SecurityConstant.TOKEN_EXPIRATION_TIME_REFRESH_TOKEN;
		
		String token = Jwts
						.builder()
						.setIssuedAt(new Date()).setIssuer(SecurityConstant.ISSUER_INFO)
						.setSubject(usuario)
						.setExpiration(new Date(System.currentTimeMillis() + time))
						.claim(SecurityConstant.AUTHORITIES, userDetails.getAuthorities())
						.signWith(SignatureAlgorithm.HS512, SecurityConstant.SUPER_SECRET_KEY)
						.compact();
		
		System.out.println("token -> "+token);
		return token;
	}
	
	public String generateJwtFromTokenRefresh(String refreshToken) {
		String username = this.getUserNameFromJwtToken(refreshToken);
		log.info("username {}", username);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		String token = Jwts
						.builder()
						.setIssuedAt(new Date()).setIssuer(SecurityConstant.ISSUER_INFO)
						.setSubject(userDetails.getUsername())
						.setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.TOKEN_EXPIRATION_TIME_REFRESH_TOKEN))
						.claim(SecurityConstant.AUTHORITIES, userDetails.getAuthorities())
						.signWith(SignatureAlgorithm.HS512, SecurityConstant.SUPER_SECRET_KEY)
						.compact();
		
		log.info("token {}", token);
		return token;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(SecurityConstant.SUPER_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) throws SecurityException  {
		log.info("validateJwtToken");
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
		log.info("Parse Jwt");
		String headerAuth = request.getHeader(SecurityConstant.HEADER_AUTHORIZACION_KEY);
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(SecurityConstant.TOKEN_BEARER_PREFIX)) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;		
	}
}
