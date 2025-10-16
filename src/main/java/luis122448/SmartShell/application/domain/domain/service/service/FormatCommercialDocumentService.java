package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.FormatCommercialDocumentDTO;
import luis122448.SmartShell.application.domain.persistence.entity.FormatCommercialDocumentEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface FormatCommercialDocumentService{
    ApiResponseList<FormatCommercialDocumentEntity> findAll(String status) throws GenericListServiceException;
    ApiResponseList<FormatCommercialDocumentEntity> findByTypcomdoc(Integer typcomdoc, String status) throws GenericListServiceException;
    ApiResponseObject<FormatCommercialDocumentEntity> findById(FormatCommercialDocumentDTO dto) throws GenericObjectServiceException;
}
