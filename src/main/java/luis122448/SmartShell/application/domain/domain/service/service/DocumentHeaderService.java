package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.persistence.projection.DocumentKardexPrint;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.model.DocumentGenericSearchFilterDTO;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentGenericSearch;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentGenericPrint;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import org.springframework.data.domain.Pageable;

public interface DocumentHeaderService{

    ApiResponseObject<DocumentHeaderEntity> findByNumint(Long numint) throws GenericObjectServiceException;
    ApiResponseList<DocumentGenericSearch> searchDocumentGeneric(DocumentGenericSearchFilterDTO t) throws GenericListServiceException;
    ApiResponsePage<DocumentGenericSearch> pageDocumentGeneric(DocumentGenericSearchFilterDTO t, Pageable pageable) throws GenericPageServiceException;
    ApiResponseObject<DocumentHeaderEntity> registerDocumentHeader(DocumentHeaderEntity t) throws GenericObjectServiceException;
    ApiResponseList<DocumentGenericPrint> printDocumentGeneric(Long numint) throws GenericListServiceException;
    ApiResponseList<DocumentKardexPrint> printDocumentKardex(Long numint) throws GenericListServiceException;

    ApiResponseObject<?> calculateImportDocument(Long numint) throws GenericProcedureException;
    ApiResponseObject<?> approvedImportDocument(Long numint) throws GenericProcedureException;
    ApiResponseObject<?> onAccountImportDocument(Long numint) throws GenericProcedureException;
    ApiResponseObject<?> cancelImportDocument(Long numint, String commen) throws GenericProcedureException;
    ApiResponseObject<?> deleteImportDocument(Long numint, String commen) throws GenericProcedureException;

}