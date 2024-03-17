package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.model.DocumentInvoiceSearchFilterDTO;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoiceSearch;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoicePrint;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import org.springframework.data.domain.Pageable;

public interface DocumentHeaderService{

    ApiResponseObject<DocumentHeaderEntity> findByNumint(Long numint) throws GenericObjectServiceException;
    ApiResponseList<DocumentInvoiceSearch> searchDocumentInvoice(DocumentInvoiceSearchFilterDTO t) throws GenericListServiceException;
    ApiResponsePage<DocumentInvoiceSearch> pageDocumentInvoice(DocumentInvoiceSearchFilterDTO t, Pageable pageable) throws GenericPageServiceException;
    ApiResponseObject<DocumentHeaderEntity> registerDocumentHeader(DocumentHeaderEntity t) throws GenericObjectServiceException;
    ApiResponseList<DocumentInvoicePrint> printDocumentInvoice(Long numint) throws GenericListServiceException;
    ApiResponseObject<?> calculateImportDocument(Long numint) throws GenericProcedureException;
    ApiResponseObject<?> approvedImportDocument(Long numint) throws GenericProcedureException;
    ApiResponseObject<?> onAccountImportDocument(Long numint) throws GenericProcedureException;
    ApiResponseObject<?> cancelImportDocument(Long numint, String commen) throws GenericProcedureException;
    ApiResponseObject<?> deleteImportDocument(Long numint, String commen) throws GenericProcedureException;
}