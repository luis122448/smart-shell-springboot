package luis122448.SmartShell.application.domain.domain.usecase;

import luis122448.SmartShell.application.domain.domain.model.*;
import luis122448.SmartShell.application.domain.persistence.mapper.DocumentInvoiceMapper;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.persistence.mapper.DocumentDetailMapper;
import luis122448.SmartShell.application.domain.persistence.mapper.DocumentHeaderMapper;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentDetailService;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentHeaderService;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentDetailEntity;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentInvoiceUseCase {

    private final DocumentHeaderService documentHeaderService;
    private final DocumentHeaderMapper documentHeaderMapper;
    private final DocumentDetailService documentDetailService;
    private final DocumentDetailMapper documentDetailMapper;
    private final DocumentInvoiceMapper documentInvoiceMapper;
    public DocumentInvoiceUseCase(DocumentHeaderService documentHeaderService, DocumentHeaderMapper documentHeaderMapper, DocumentDetailService documentDetailService, DocumentDetailMapper documentDetailMapper, DocumentInvoiceMapper documentInvoiceMapper) {
        this.documentHeaderService = documentHeaderService;
        this.documentHeaderMapper = documentHeaderMapper;
        this.documentDetailService = documentDetailService;
        this.documentDetailMapper = documentDetailMapper;
        this.documentInvoiceMapper = documentInvoiceMapper;
    }

    public ApiResponseObject<DocumentInvoiceBasicDTO> registerDocument(DocumentInvoiceRegisterDTO t) throws GenericObjectServiceException, GenericProcedureException {
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
        return new ApiResponseObject<DocumentInvoiceBasicDTO>(1,"Ok", Optional.of(new DocumentInvoiceBasicDTO(numint,"", 0L)));
    }

    public ApiResponseList<DocumentInvoicePrintDTO> printDocument(Long numint) throws GenericListServiceException {
        List<DocumentInvoicePrintDTO> obj =  this.documentInvoiceMapper.toListDocumentInvoicePrintDTO(this.documentHeaderService.printDocumentInvoice(numint).getList().orElseThrow());
        return new ApiResponseList<DocumentInvoicePrintDTO>(1,"Ok",Optional.of(obj));
    }

    public ApiResponseList<DocumentInvoiceSearchDTO> searchDocument(DocumentInvoiceSearchFilterDTO t) throws GenericListServiceException {
        List<DocumentInvoiceSearchDTO> obj = this.documentInvoiceMapper.toListDocumentInvoiceSearchDTO(this.documentHeaderService.searchDocumentInvoice(t).getList().orElseThrow());
        return new ApiResponseList<DocumentInvoiceSearchDTO>(1,"Ok", Optional.of(obj));
    }
}
