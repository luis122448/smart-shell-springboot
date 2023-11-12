package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.SituationCommercialDocumentService;
import luis122448.SmartShell.application.domain.persistence.entity.SituationCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.repository.SituationCommercialDocumentRepository;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SituationCommercialDocumentServiceImpl implements SituationCommercialDocumentService {

    private final SituationCommercialDocumentRepository situationCommercialDocumentRepository;
    public SituationCommercialDocumentServiceImpl(SituationCommercialDocumentRepository situationCommercialDocumentRepository) {
        this.situationCommercialDocumentRepository = situationCommercialDocumentRepository;
    }

    @Override
    public ApiResponseList<SituationCommercialDocumentEntity> findAll(SituationCommercialDocumentEntity situationCommercialDocumentEntity) throws GenericListServiceException {
        return null;
    }

    @Override
    public ApiResponseList<SituationCommercialDocumentEntity> findByLike(SituationCommercialDocumentEntity t) throws GenericListServiceException {
        return new ApiResponseList<SituationCommercialDocumentEntity>(1, "Ok", Optional.of(this.situationCommercialDocumentRepository.findByLike(t.typcomdoc)));
    }

    @Override
    public ApiResponseObject<SituationCommercialDocumentEntity> findById(SituationCommercialDocumentEntity situationCommercialDocumentEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<SituationCommercialDocumentEntity> save(SituationCommercialDocumentEntity situationCommercialDocumentEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<SituationCommercialDocumentEntity> update(SituationCommercialDocumentEntity situationCommercialDocumentEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<SituationCommercialDocumentEntity> delete(SituationCommercialDocumentEntity situationCommercialDocumentEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<SituationCommercialDocumentEntity> undelete(SituationCommercialDocumentEntity situationCommercialDocumentEntity) throws GenericObjectServiceException {
        return null;
    }
}
