package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ReasonCommercialDocumentService;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.repository.ReasonCommercialDocumentRepository;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReasonCommercialDocumentServiceImpl implements ReasonCommercialDocumentService {

    private final ReasonCommercialDocumentRepository reasonCommercialDocumentRepository;
    public ReasonCommercialDocumentServiceImpl(ReasonCommercialDocumentRepository reasonCommercialDocumentRepository) {
        this.reasonCommercialDocumentRepository = reasonCommercialDocumentRepository;
    }

    @Override
    public ApiResponseList<ReasonCommercialDocumentEntity> findAll(ReasonCommercialDocumentEntity reasonCommercialDocumentEntity) throws GenericListServiceException {
        return new ApiResponseList<ReasonCommercialDocumentEntity>(1,"Ok", Optional.of(this.reasonCommercialDocumentRepository.findAll()));
    }

    @Override
    public ApiResponseList<ReasonCommercialDocumentEntity> findByLike(ReasonCommercialDocumentEntity t) throws GenericListServiceException {
        return new ApiResponseList<ReasonCommercialDocumentEntity>(1,"Ok", Optional.of(this.reasonCommercialDocumentRepository.findByLike(t.typcomdoc,t.ingsalcom)));
    }

    @Override
    public ApiResponseObject<ReasonCommercialDocumentEntity> findById(ReasonCommercialDocumentEntity reasonCommercialDocumentEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ReasonCommercialDocumentEntity> save(ReasonCommercialDocumentEntity reasonCommercialDocumentEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ReasonCommercialDocumentEntity> update(ReasonCommercialDocumentEntity reasonCommercialDocumentEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ReasonCommercialDocumentEntity> delete(ReasonCommercialDocumentEntity reasonCommercialDocumentEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ReasonCommercialDocumentEntity> undelete(ReasonCommercialDocumentEntity reasonCommercialDocumentEntity) throws GenericObjectServiceException {
        return null;
    }
}
