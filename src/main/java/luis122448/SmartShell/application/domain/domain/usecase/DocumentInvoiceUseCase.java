package luis122448.SmartShell.application.domain.domain.usecase;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.model.*;
import luis122448.SmartShell.application.domain.persistence.mapper.DocumentGenericMapper;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentGenericDetailModify;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.persistence.mapper.DocumentDetailMapper;
import luis122448.SmartShell.application.domain.persistence.mapper.DocumentHeaderMapper;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentDetailService;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentHeaderService;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentDetailEntity;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DocumentInvoiceUseCase {

    private final DocumentHeaderService documentHeaderService;
    private final DocumentHeaderMapper documentHeaderMapper;
    private final DocumentDetailService documentDetailService;
    private final DocumentDetailMapper documentDetailMapper;
    private final DocumentGenericMapper documentGenericMapper;

    public DocumentInvoiceUseCase(DocumentHeaderService documentHeaderService, DocumentHeaderMapper documentHeaderMapper, DocumentDetailService documentDetailService, DocumentDetailMapper documentDetailMapper, DocumentGenericMapper documentGenericMapper) {
        this.documentHeaderService = documentHeaderService;
        this.documentHeaderMapper = documentHeaderMapper;
        this.documentDetailService = documentDetailService;
        this.documentDetailMapper = documentDetailMapper;
        this.documentGenericMapper = documentGenericMapper;
    }

    public ApiResponseObject<DocumentGenericBasicDTO> registerDocument(DocumentGenericRegisterDTO t) throws GenericObjectServiceException, GenericProcedureException {
        DocumentHeaderEntity objectHeader = this.documentHeaderMapper.toDocumentHeaderEntity(t.getHeader());
        objectHeader.setNumint(0L);
        ApiResponseObject<DocumentHeaderEntity> responseHeader = this.documentHeaderService.registerDocumentHeader(objectHeader);
        if ( responseHeader.getStatus() < 0 ){
            throw new GenericObjectServiceException(responseHeader.getMessage(),responseHeader.getLogMessage());
        }
        Long numint = responseHeader.getObject().orElseThrow().getNumint();
        List<DocumentDetailEntity> listDetail = this.documentDetailMapper.toListDocumentDetailEntity(t.getDetails());
        log.info("List Detail: {}",listDetail);
        for ( DocumentDetailEntity tmp : listDetail ){
            tmp.setNumint(numint);
            tmp.setNumite(0L);
            ApiResponseObject<?> responseDetail = this.documentDetailService.registerDocumentDetail(tmp);
            if (responseDetail.getStatus() < 0) {
                throw new GenericObjectServiceException(responseDetail.getMessage(),responseDetail.getLogMessage());
            }
        }
        ApiResponseObject<?> objectCalculate = this.documentHeaderService.calculateImportDocument(numint);
        if (objectCalculate.getStatus() < 0){
            throw new GenericObjectServiceException(objectCalculate.getMessage(),objectCalculate.getLogMessage());
        }
        return new ApiResponseObject<>( Optional.of(new DocumentGenericBasicDTO(numint,"", 0L)));
    }

    public ApiResponseObject<DocumentGenericBasicDTO> modifyDocument(DocumentGenericRegisterDTO t) throws GenericObjectServiceException, GenericProcedureException {
        DocumentHeaderEntity objectHeader = this.documentHeaderMapper.toDocumentHeaderEntity(t.getHeader());
        ApiResponseObject<DocumentHeaderEntity> responseHeader = this.documentHeaderService.registerDocumentHeader(objectHeader);
        if ( responseHeader.getStatus() < 0 ){
            throw new GenericObjectServiceException(responseHeader.getMessage(),responseHeader.getLogMessage());
        }
        Long numint = responseHeader.getObject().orElseThrow().getNumint();
        List<DocumentDetailEntity> listDetail = this.documentDetailMapper.toListDocumentDetailEntity(t.getDetails());
        for ( DocumentDetailEntity tmp : listDetail ){
            tmp.setNumint(numint);
            ApiResponseObject<?> responseDetail = this.documentDetailService.registerDocumentDetail(tmp);
            if (responseDetail.getStatus() < 0) {
                throw new GenericObjectServiceException(responseDetail.getMessage(),responseDetail.getLogMessage());
            }
        }
        ApiResponseObject<?> objectCalculate = this.documentHeaderService.calculateImportDocument(numint);
        if (objectCalculate.getStatus() < 0){
            throw new GenericObjectServiceException(objectCalculate.getMessage(),objectCalculate.getLogMessage());
        }
        return new ApiResponseObject<>( Optional.of(new DocumentGenericBasicDTO(numint,"", 0L)));
    }

    public ApiResponseObject<DocumentGenericModifyDTO> findByNumint(Long numint) throws GenericObjectServiceException, GenericListServiceException {
        DocumentHeaderEntity objectHeader = this.documentHeaderService.findByNumint(numint).getObject().orElseThrow();
        List<DocumentGenericDetailModify> listDetail = this.documentDetailService.searchDocumentGenericDetail(numint).getList().orElseThrow();
        DocumentGenericModifyDTO obj = new DocumentGenericModifyDTO(this.documentHeaderMapper.toDocumentHeaderDTO(objectHeader),this.documentGenericMapper.toListDocumentGenericDetailModifyDTO(listDetail));
        return new ApiResponseObject<>(Optional.of(obj));
    }

    public ApiResponsePage<DocumentGenericSearchDTO> searchPageDocument(DocumentGenericSearchFilterDTO t, Pageable pageable) throws GenericPageServiceException {
        Page<DocumentGenericSearchDTO> obj = this.documentGenericMapper.toPageDocumentGenericSearchDTO(this.documentHeaderService.pageDocumentGeneric(t,pageable).getPage().orElseThrow());
        return new ApiResponsePage<>(Optional.of(obj));
    }

    public ApiResponseObject<?> approvedDocument(Long numint) throws GenericProcedureException {
        return this.documentHeaderService.approvedImportDocument(numint);
    }

    public ApiResponseObject<?> onAccountDocument(Long numint) throws GenericProcedureException {
        return this.documentHeaderService.onAccountImportDocument(numint);
    }

    public ApiResponseObject<?> cancelDocument(Long numint, String commen) throws GenericProcedureException {
        return this.documentHeaderService.cancelImportDocument(numint,commen);
    }

    public ApiResponseObject<?> deleteDocument(Long numint, String commen) throws GenericProcedureException {
        return this.documentHeaderService.deleteImportDocument(numint,commen);
    }
}
