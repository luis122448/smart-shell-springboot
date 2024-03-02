package luis122448.SmartShell.application.domain.domain.service.implement;

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

    public TypeBusinessPartnerServiceImpl(TypeBusinessPartnerRepository typeBusinessPartnerRepository) {
        this.typeBusinessPartnerRepository = typeBusinessPartnerRepository;
    }

    @Override
    public ApiResponseList<TypeBusinessPartnerEntity> findAll() throws GenericListServiceException {
        List<TypeBusinessPartnerEntity> list = typeBusinessPartnerRepository.findAll();
        if (list.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<TypeBusinessPartnerEntity>(Optional.of(list));
    }
}
