package luis122448.SmartShell.application.domain.domain.service.implement;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.service.service.UserService;
import luis122448.SmartShell.application.domain.persistence.entity.UserEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.UserKey;
import luis122448.SmartShell.application.domain.persistence.repository.UserRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Qualifier("UserRepository")
    private final UserRepository userRepository;
    private final SecurityContextInitializer securityContextInitializer;
    public UserServiceImpl(UserRepository userRepository, SecurityContextInitializer securityContextInitializer) {
        this.userRepository = userRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseObject<UserEntity> findByProfile() throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        String coduser = securityContextInitializer.getCodUser();
        log.info("idcompany: {}, coduser: {}", idcompany, coduser);
        return new ApiResponseObject<>(userRepository.findById(new UserKey(idcompany,coduser)));
    }
}
