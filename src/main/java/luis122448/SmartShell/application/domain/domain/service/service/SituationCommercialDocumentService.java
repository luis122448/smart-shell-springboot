package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.persistence.entity.SituationCommercialDocumentEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface SituationCommercialDocumentService{
    ApiResponseList<SituationCommercialDocumentEntity> findAll(String status) throws GenericListServiceException;
    ApiResponseList<SituationCommercialDocumentEntity> findByTypcomdoc(Integer typcomdoc, String status) throws GenericListServiceException;

}
