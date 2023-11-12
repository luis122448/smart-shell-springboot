package luis122448.SmartShell.security.auth.jwt;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.security.auth.jwt.JWTUtils;
import luis122448.SmartShell.security.constant.*;

@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("doFilterInternal");
		try {
			// Valida el Token del Header
			String jwt = this.jwtUtils.parseJwt(request);
			log.info("jwt  {}", jwt);
			
			if (jwt!= null && this.jwtUtils.validateJwtToken(jwt)) {

				String username = this.jwtUtils.getUserNameFromJwtToken(jwt);
				//log.info("username {}", username);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, userDetails.getPassword(), userDetails.getAuthorities());

				String token = this.jwtUtils.generateJwtToken(userDetails.getUsername(), false);

				log.info("token doFilterInternal " + token);

				response.addHeader("Access-Control-Expose-Headers", "Authorization");

				response.addHeader(SecurityConstant.HEADER_AUTHORIZACION_KEY, SecurityConstant.TOKEN_BEARER_PREFIX + " " + token);
				log.info("authentication {}", authentication);
				 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			 else {
				log.info("No Token");
			}
		} catch (SecurityException e){
			handleAuthenticationException(response, e);
		} finally {
			log.info("doFilter");
			filterChain.doFilter(request, response);
		}
	}

	private void handleAuthenticationException(HttpServletResponse response, SecurityException e) throws IOException {
		log.info("handleAuthenticationException {}", e.getMessage());
		ApiResponseObject<?> apiResponseObject = new ApiResponseObject<Object>(-401, e.getMessage(), e.getCause().getMessage(), Optional.empty());

		// Convert apiResponseObject a JSON
		String jsonResponse = objectMapper.writeValueAsString(apiResponseObject);
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(jsonResponse);
	}

}
