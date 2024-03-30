package luis122448.SmartShell.security.auth.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException, ServletException {
		String logMessage = "NO CAUSE AVAILABLE";
		if(authException.getCause() != null){
			logMessage = authException.getCause().getMessage();
		}

		ApiResponseObject<?> obj = new ApiResponseObject<>(-1, authException.getMessage(), logMessage, Optional.empty());

		// Agregar información de traza de error solo si es el primer error
//		if (response != null) {
//			response.setContentType("application/json");
//			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.registerModule(new JavaTimeModule());
//			objectMapper.registerModule(new Jdk8Module());
//			log.info("JWTAuthEntryPoint {}", obj.toString());
//			String jsonResponse = objectMapper.writeValueAsString(obj);
//			response.getWriter().write(jsonResponse);
//		}
	}

}
