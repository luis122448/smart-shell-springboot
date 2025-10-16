package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.SituationCommercialDocumentService;
import luis122448.SmartShell.application.domain.persistence.entity.SituationCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.repository.SituationCommercialDocumentRepository;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SituationCommercialDocumentServiceImpl implements SituationCommercialDocumentService {
    private final SituationCommercialDocumentRepository situationCommercialDocumentRepository;
    private final SecurityContextInitializer securityContextInitializer;
    public SituationCommercialDocumentServiceImpl(SituationCommercialDocumentRepository situationCommercialDocumentRepository, SecurityContextInitializer securityContextInitializer) {
        this.situationCommercialDocumentRepository = situationCommercialDocumentRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<SituationCommercialDocumentEntity> findAll(String status) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<SituationCommercialDocumentEntity> entityList = this.situationCommercialDocumentRepository.findByIdcompany(idcompany,status);
        if (entityList.isEmpty()){
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(entityList));
    }

    @Override
    public ApiResponseList<SituationCommercialDocumentEntity> findByTypcomdoc(Integer typcomdoc, String status) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<SituationCommercialDocumentEntity> entityList = this.situationCommercialDocumentRepository.findByIdcompanyAndTypcomdoc(idcompany,typcomdoc,status);
        if (entityList.isEmpty()){
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(entityList));
    }

}
