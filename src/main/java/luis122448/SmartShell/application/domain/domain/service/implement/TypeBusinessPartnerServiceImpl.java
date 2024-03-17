package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.service.service.TypeBusinessPartnerService;
import luis122448.SmartShell.application.domain.persistence.entity.TypeBusinessPartnerEntity;
import luis122448.SmartShell.application.domain.persistence.repository.TypeBusinessPartnerRepository;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeBusinessPartnerServiceImpl implements TypeBusinessPartnerService {
    private final TypeBusinessPartnerRepository typeBusinessPartnerRepository;
    private final SecurityContextInitializer securityContextInitializer;
    public TypeBusinessPartnerServiceImpl(TypeBusinessPartnerRepository typeBusinessPartnerRepository, SecurityContextInitializer securityContextInitializer) {
        this.typeBusinessPartnerRepository = typeBusinessPartnerRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<TypeBusinessPartnerEntity> findAll() throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<TypeBusinessPartnerEntity> list = typeBusinessPartnerRepository.findByIdcompany(idcompany);
        if (list.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(list));
    }
}
