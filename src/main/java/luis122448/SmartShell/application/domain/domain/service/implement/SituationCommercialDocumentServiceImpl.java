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
    public ApiResponseList<SituationCommercialDocumentEntity> findAll() throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<SituationCommercialDocumentEntity> lst = this.situationCommercialDocumentRepository.findByIdcompany(idcompany);
        if (lst.isEmpty()){
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(lst));
    }

    @Override
    public ApiResponseList<SituationCommercialDocumentEntity> findByTypcomdoc(SituationCommercialDocumentEntity t) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<SituationCommercialDocumentEntity> lst = this.situationCommercialDocumentRepository.findByIdcompanyAndTypcomdoc(idcompany,t.getTypcomdoc());
        if (lst.isEmpty()){
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(lst));
    }

}
