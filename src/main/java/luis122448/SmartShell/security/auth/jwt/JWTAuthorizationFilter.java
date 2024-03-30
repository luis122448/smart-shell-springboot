package luis122448.SmartShell.security.auth.jwt;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.auth.user.UserDetailsCustom;
import luis122448.SmartShell.security.auth.user.UserDetailsServiceCustom;
import luis122448.SmartShell.security.utility.constant.SecurityConstant;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter{
	@Autowired
	private UserDetailsServiceCustom userDetailsServiceCustom;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private ObjectMapper objectMapper;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = this.jwtUtils.parseJwt(request);
			if (jwt!= null && this.jwtUtils.validateJwtToken(jwt)) {
				log.info("Token: {}",jwt);
				Map<String, Object> tokenData = this.jwtUtils.getDataJwtToken(jwt);
				String company = tokenData.get(SecurityConstant.COMPANY).toString();
				String coduser = tokenData.get(SecurityConstant.CODUSER).toString();
				UserDetailsCustom userDetailsCustom = this.userDetailsServiceCustom.loadUserByUsernameAndCompany(company, coduser);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetailsCustom, null, userDetailsCustom.getAuthorities());
//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//						userDetailsCustom, userDetailsCustom.getPassword(), userDetailsCustom.getAuthorities());
//
//				String token = this.jwtUtils.generateJwtToken(company, coduser, false);
//				response.addHeader("Access-Control-Expose-Headers", "Authorization");
//				response.addHeader(SecurityConstant.HEADER_AUTHORIZACION_KEY, SecurityConstant.TOKEN_BEARER_PREFIX + " " + token);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			else {
				log.info("No Token");
			}
		} catch (SecurityException | GenericAuthServiceException e){
			log.info("SecurityException | GenericAuthServiceException {}", e.getMessage());
			handleAuthenticationException(response, e);
		} finally {
			filterChain.doFilter(request, response);
		}
	}

	private void handleAuthenticationException(HttpServletResponse response, Exception e) throws IOException {
		log.info("handleAuthenticationException {}", e.getMessage());
		String logMessage = e.getMessage();
		if (e.getCause() != null){
			logMessage = e.getCause().getMessage();
		}
		ApiResponseObject<?> apiResponseObject = new ApiResponseObject<>(-401, e.getMessage(), logMessage, Optional.empty());

		// Convert apiResponseObject a JSON
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new Jdk8Module());
		String jsonResponse = objectMapper.writeValueAsString(apiResponseObject);
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(jsonResponse);
	}

}
