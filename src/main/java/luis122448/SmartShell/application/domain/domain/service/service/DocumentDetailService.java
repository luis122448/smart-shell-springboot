package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoiceDetailModify;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentDetailEntity;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface DocumentDetailService {

    ApiResponseList<DocumentDetailEntity> findByNumint(Long numint) throws GenericListServiceException;
    ApiResponseList<DocumentInvoiceDetailModify> searchDocumentInvoiceDetail(Long numint) throws GenericListServiceException;
    ApiResponseObject<DocumentDetailEntity> registerDocumentDetail(DocumentDetailEntity t) throws GenericObjectServiceException;

}
