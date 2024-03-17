package luis122448.SmartShell.security.application.service.service;

import luis122448.SmartShell.security.application.entity.UserEntity;
import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.application.service.model.VerifyCodeModel;
import luis122448.SmartShell.security.auth.user.UserDetailsCustom;

import java.util.Optional;

public interface VerifyCodeService {
    Optional<VerifyCodeModel> createCode(Integer idcompany,String company, String coduser) throws GenericAuthServiceException;
    Optional<UserEntity> verifyCode(String company, String coduser, String code) throws GenericAuthServiceException;
}
