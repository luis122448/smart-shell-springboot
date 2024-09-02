package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface ReasonCommercialDocumentService{
    ApiResponseList<ReasonCommercialDocumentEntity> findAll() throws GenericListServiceException;
    ApiResponseList<ReasonCommercialDocumentEntity> findByTypcomdocAndinout(ReasonCommercialDocumentEntity t) throws GenericListServiceException;
}
