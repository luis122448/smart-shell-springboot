package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.ReasonCommercialDocumentDTO;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface ReasonCommercialDocumentService extends GenericService<ReasonCommercialDocumentEntity, ReasonCommercialDocumentDTO> {
    ApiResponseList<ReasonCommercialDocumentEntity> findAll(String status) throws GenericListServiceException;
    ApiResponseList<ReasonCommercialDocumentEntity> findByTypcomdocAndInout(Integer typcomdoc, Integer inout, String status) throws GenericListServiceException;
}
