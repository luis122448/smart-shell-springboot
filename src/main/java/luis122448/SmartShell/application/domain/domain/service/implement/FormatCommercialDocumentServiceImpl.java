package luis122448.SmartShell.application.domain.domain.service.implement;

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

@Service
public class FormatCommercialDocumentServiceImpl implements FormatCommercialDocumentService {

    private final FormatCommercialDocumentRepository formatCommercialDocumentRepository;

    public FormatCommercialDocumentServiceImpl(FormatCommercialDocumentRepository formatCommercialDocumentRepository) {
        this.formatCommercialDocumentRepository = formatCommercialDocumentRepository;
    }

    @Override
    public ApiResponseList<FormatCommercialDocumentEntity> findAll() throws GenericListServiceException {
        List<FormatCommercialDocumentEntity> lst = this.formatCommercialDocumentRepository.findAll();
        if (lst.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(lst));
    }

    @Override
    public ApiResponseList<FormatCommercialDocumentEntity> findByLike(FormatCommercialDocumentEntity formatCommercialDocumentEntity) throws GenericListServiceException {
        List<FormatCommercialDocumentEntity> lst = this.formatCommercialDocumentRepository.findByTypcomdoc(formatCommercialDocumentEntity.getTypcomdoc());
        if (lst.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(lst));
    }

    @Override
    public ApiResponseObject<FormatCommercialDocumentEntity> findById(FormatCommercialDocumentPK formatCommercialDocumentPK) throws GenericObjectServiceException {
        return new ApiResponseObject<>(this.formatCommercialDocumentRepository.findById(formatCommercialDocumentPK));
    }

}
