package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.model.DocumentInvoiceSearchFilterDTO;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoiceSearch;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoicePrint;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface DocumentHeaderService{

    ApiResponseObject<DocumentHeaderEntity> findById(DocumentHeaderEntity t) throws GenericObjectServiceException;
    ApiResponseList<DocumentInvoiceSearch> searchDocumentInvoice(DocumentInvoiceSearchFilterDTO t) throws GenericListServiceException;
    ApiResponseObject<DocumentHeaderEntity> registerDocumentHeader(DocumentHeaderEntity t) throws GenericObjectServiceException;
    ApiResponseList<DocumentInvoicePrint> printDocumentInvoice(Long numint) throws GenericListServiceException;
    ApiResponseObject<?> calculateImportDocument(Long numint) throws GenericProcedureException;
}