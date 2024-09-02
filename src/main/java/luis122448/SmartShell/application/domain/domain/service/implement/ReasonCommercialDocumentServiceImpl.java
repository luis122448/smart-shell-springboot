package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ReasonCommercialDocumentService;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.repository.ReasonCommercialDocumentRepository;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReasonCommercialDocumentServiceImpl implements ReasonCommercialDocumentService {
    private final ReasonCommercialDocumentRepository reasonCommercialDocumentRepository;
    private final SecurityContextInitializer securityContextInitializer;
    public ReasonCommercialDocumentServiceImpl(ReasonCommercialDocumentRepository reasonCommercialDocumentRepository, SecurityContextInitializer securityContextInitializer) {
        this.reasonCommercialDocumentRepository = reasonCommercialDocumentRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<ReasonCommercialDocumentEntity> findAll() throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<ReasonCommercialDocumentEntity> lst = this.reasonCommercialDocumentRepository.findByIdcompany(idcompany);
        if (lst.isEmpty()){
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(lst));
    }

    @Override
    public ApiResponseList<ReasonCommercialDocumentEntity> findByTypcomdocAndinout(ReasonCommercialDocumentEntity t) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<ReasonCommercialDocumentEntity> list = this.reasonCommercialDocumentRepository.findByIdcompanyAndTypcomdocAndInout(idcompany,t.typcomdoc,t.inout);
        if (list.isEmpty()){
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(list));
    }

}
