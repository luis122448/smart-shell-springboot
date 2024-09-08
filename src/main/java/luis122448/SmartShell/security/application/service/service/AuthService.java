package luis122448.SmartShell.security.application.service.service;

import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.application.service.model.LoginModel;
import luis122448.SmartShell.security.application.service.model.RefreshTokenModel;
import luis122448.SmartShell.security.application.service.model.UserModel;
import luis122448.SmartShell.security.application.service.model.VerifyCodeModel;
import luis122448.SmartShell.security.application.utility.ApiResponseAuth;
import luis122448.SmartShell.security.application.utility.ApiResponseMetadata;

public interface AuthService {
	ApiResponseMetadata<?,?> login(LoginModel loginModel) throws GenericAuthServiceException;

	ApiResponseMetadata<?,?> verifyCode(VerifyCodeModel verifyCodeModel) throws GenericAuthServiceException;

	ApiResponseAuth<?> refreshToken(RefreshTokenModel refreshTokenModel) throws GenericAuthServiceException;
}
