package luis122448.SmartShell.security.auth.filter;

import luis122448.SmartShell.security.auth.jwt.JWTAuthEntryPoint;
import luis122448.SmartShell.security.auth.jwt.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration // Importa!, gracias a este método sobreescribe la configuración
@EnableWebSecurity
public class SpringSecurityConfiguration {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		try {
			// Deshabilita la protección CSRF (Cross-Site Request Forgery) y la configuración de CORS (Cross-Origin Resource Sharing) en la aplicación.
			http.csrf().disable();
			http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(new JWTAuthEntryPoint());
			// Establece la política de gestión de sesiones en "STATELESS", lo que significa que no se mantendrán sesiones en el servidor y 
			// cada solicitud debe incluir la información de autenticación necesaria.
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/v1/auth/**").permitAll());
			http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/v1/ecommerce/**").permitAll());
			http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api-docs/**").permitAll());
			http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/swagger-ui/**").permitAll());
			http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/test/**").permitAll());
			http.authorizeHttpRequests(authorize ->
			authorize
				.requestMatchers("/v1/facturacion/**").hasAnyRole("ADMIN","TEST","MASTER","USER","PUBLIC")
				.requestMatchers("/v1/configuracion/**").hasAnyRole("ADMIN","TEST","MASTER","PUBLIC")
				.requestMatchers("/archive/**").hasAnyRole("ADMIN","TEST","MASTER","PUBLIC")
				.anyRequest()
				.authenticated()
				);
			// Configura el proveedor de autorización
			http.authenticationProvider(authenticationProvider());
			// Agrega un filtro personalizado, para realizar la autorización basada JWT
			http.addFilterBefore(jWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
			return http.build();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	@Bean
	public JWTAuthorizationFilter jWTAuthorizationFilter() {
		return new JWTAuthorizationFilter();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		log.info("Load authentication provider...");
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(this.userDetailsService);
		authProvider.setPasswordEncoder(this.bCryptPasswordEncoder);
		return authProvider;
	}
}
