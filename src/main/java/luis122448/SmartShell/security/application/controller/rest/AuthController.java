package luis122448.SmartShell.security.application.controller.rest;

import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.application.service.model.LoginModel;
import luis122448.SmartShell.security.application.service.model.RefreshTokenModel;
import luis122448.SmartShell.security.application.service.model.VerifyCodeModel;
import luis122448.SmartShell.security.application.service.service.AuthService;
import luis122448.SmartShell.security.application.utility.ApiResponseAuth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static luis122448.SmartShell.security.utility.constant.SecurityConstant.API_AUTH;

@RestController
@RequestMapping(API_AUTH)
public class AuthController {
	
	private final AuthService authService;
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginModel loginModel) throws GenericAuthServiceException {
		ApiResponseAuth<?> response = this.authService.login(loginModel);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/verify-code")
	public ResponseEntity<?> verifyCode(@RequestBody VerifyCodeModel verifyCodeModel) throws GenericAuthServiceException {
		ApiResponseAuth<?> response = this.authService.verifyCode(verifyCodeModel);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenModel refreshTokenModel) throws GenericAuthServiceException{
		ApiResponseAuth<?> response = this.authService.refreshToken(refreshTokenModel);
		return ResponseEntity.ok(response);
	}
	
}
