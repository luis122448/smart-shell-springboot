package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.security.application.entity.UserEntity;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface UserService {
    ApiResponseObject<UserEntity> findByProfile() throws GenericObjectServiceException;
}
