package luis122448.SmartShell.security.auth.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // .allowedOrigins(
                        //         "http://localhost:*",
                        //         "http://smart-shell-angular:*",
                        //         "https://smart-shell.luis122448.com",
                        //         "http://smart-shell.luis122448.com"
                        // )
                        .allowedOriginPatterns(
                                "http://localhost:*",
                                "http://smart-shell-angular:*",
                                "https://smart-shell.luis122448.com",
                                "http://smart-shell.luis122448.com"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

}