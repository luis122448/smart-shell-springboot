package luis122448.SmartShell.security.application.service.service;

import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.application.service.model.LoginModel;
import luis122448.SmartShell.security.application.service.model.RefreshTokenModel;
import luis122448.SmartShell.security.application.service.model.UserModel;
import luis122448.SmartShell.security.application.service.model.VerifyCodeModel;
import luis122448.SmartShell.security.application.utility.ApiResponseAuth;

public interface AuthService {
	ApiResponseAuth<?> login(LoginModel loginModel) throws GenericAuthServiceException;

	ApiResponseAuth<?> verifyCode(VerifyCodeModel verifyCodeModel) throws GenericAuthServiceException;

	ApiResponseAuth<?> refreshToken(RefreshTokenModel refreshTokenModel) throws GenericAuthServiceException;
}
