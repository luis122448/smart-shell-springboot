package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.FormatCommercialDocumentDTO;
import luis122448.SmartShell.application.domain.domain.service.service.FormatCommercialDocumentService;
import luis122448.SmartShell.application.domain.persistence.entity.FormatCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.FormatCommercialDocumentPK;
import luis122448.SmartShell.application.domain.persistence.repository.FormatCommercialDocumentRepository;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_NOT_EXISTS;

@Service
public class FormatCommercialDocumentServiceImpl implements FormatCommercialDocumentService {

    private final FormatCommercialDocumentRepository formatCommercialDocumentRepository;
    private final SecurityContextInitializer securityContextInitializer;

    public FormatCommercialDocumentServiceImpl(FormatCommercialDocumentRepository formatCommercialDocumentRepository, SecurityContextInitializer securityContextInitializer) {
        this.formatCommercialDocumentRepository = formatCommercialDocumentRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<FormatCommercialDocumentEntity> findAll(String status) throws GenericListServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        List<FormatCommercialDocumentEntity> lst = this.formatCommercialDocumentRepository.findByIdcompany(idCompany,status);
        if (lst.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(lst));
    }

    @Override
    public ApiResponseList<FormatCommercialDocumentEntity> findByTypcomdoc(Integer typcomdoc, String status) throws GenericListServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        List<FormatCommercialDocumentEntity> lst = this.formatCommercialDocumentRepository.findByIdcompanyAndTypcomdoc(idCompany, typcomdoc, status);
        if (lst.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(lst));
    }

    @Override
    public ApiResponseObject<FormatCommercialDocumentEntity> findById(FormatCommercialDocumentDTO dto) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        FormatCommercialDocumentPK id = new FormatCommercialDocumentPK(idcompany, dto.getTypcomdoc(), dto.getTypformat());
        Optional<FormatCommercialDocumentEntity> searchEntity = this.formatCommercialDocumentRepository.findById(id);
        if (searchEntity.isEmpty()) {
            throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
        }
        return new ApiResponseObject<>(searchEntity);
    }

}
