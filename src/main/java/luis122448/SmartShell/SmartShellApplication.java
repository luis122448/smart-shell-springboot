package luis122448.SmartShell;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SMART SHELL API",
		version = "1.0.0",
		description = "Documentation for API"),
		tags = {
				@Tag(name = "Test", description = "For Testing Deploying Server"),
				@Tag(name = "Auth", description = "Authentication"),
				@Tag(name = "Document", description = "Document for Application")
		})
public class SmartShellApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartShellApplication.class, args);
		try {
			// Obtener el directorio raíz del proyecto
			String projectRoot = System.getProperty("user.dir");
			// String projectPath = ResourceUtils.getFile("classpath:").getAbsolutePath();

			// Imprimir el directorio raíz
			System.out.println("Directorio raíz del proyecto: " + projectRoot);
			// System.out.println("PATH del proyecto: " + projectPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
