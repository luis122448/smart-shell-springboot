package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.model.*;
import luis122448.SmartShell.application.domain.domain.report.service.DocumentInvoiceReport;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.usecase.DocumentInvoiceUseCase;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.util.object.api.*;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_DOCUMENT;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@RestController
@RequestMapping(PATH_BILLING + PATH_DOCUMENT + "/invoice")
public class DocumentInvoiceController {

    private final DocumentInvoiceUseCase documentInvoiceUseCase;
    private final DocumentInvoiceReport documentInvoiceReport;
    public DocumentInvoiceController(DocumentInvoiceUseCase documentInvoiceUseCase, DocumentInvoiceReport documentInvoiceReport) {
        this.documentInvoiceUseCase = documentInvoiceUseCase;
        this.documentInvoiceReport = documentInvoiceReport;
    }

    @GetMapping("/print")
    public ResponseEntity<?> printDocument(@RequestParam(name = "numint", defaultValue = "0") Long numint) throws GenericListServiceException, JRException, FileNotFoundException {
        ApiResponseByte<?> obj = new ApiResponseByte<>(this.documentInvoiceReport.invoicePrintDocument(numint),"application/pdf");
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/search-page")
    public ResponseEntity<?> searchPageDocument(@RequestParam(name = "typcomdoc", defaultValue = "") Integer typcomdoc,
                                          @RequestParam(name = "startat", defaultValue = "") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startat,
                                          @RequestParam(name = "finalat", defaultValue = "") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate finalat,
                                          @RequestParam(name = "sitcomdoc", defaultValue = "-1") String sitdomco,
                                          @RequestParam(name = "reacomdoc", defaultValue = "-1") String reacomdoc,
                                          @RequestParam(name = "codbranch", defaultValue = "-1") String codbranch,
                                          @RequestParam(name = "codplaiss", defaultValue = "-1") String codplaiss,
                                          @RequestParam(name = "serie", defaultValue = "-1") String serie,
                                          @RequestParam(name = "codcur", defaultValue = "-1") String codcur,
                                          @RequestParam(name = "codsel", defaultValue = "-1") String codsel,
                                          @RequestParam(name = "typpaycon", defaultValue = "-1") Integer typpaycon,
                                          @RequestParam(name = "codbuspar", defaultValue = "-1") String codbuspar,
                                          Pageable pageable) throws GenericPageServiceException {
        DocumentGenericSearchFilterDTO obj = new DocumentGenericSearchFilterDTO(typcomdoc,startat,finalat,sitdomco,reacomdoc,serie,typpaycon,codbuspar);
        ApiResponsePage<DocumentGenericSearchDTO> tmp = this.documentInvoiceUseCase.pageDocument(obj, pageable);
        return ResponseEntity.ok(tmp);
    }

    @GetMapping("/by-numint")
    public ResponseEntity<?> findByNumint(@RequestParam(name = "numint", defaultValue = "0") Long numint) throws GenericObjectServiceException, GenericListServiceException {
        return ResponseEntity.ok(this.documentInvoiceUseCase.findByNumint(numint));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDocument(@RequestBody DocumentGenericRegisterDTO obj) throws GenericObjectServiceException, GenericProcedureException {
        ApiResponseObject<DocumentGenericBasicDTO> tmp = this.documentInvoiceUseCase.registerDocument(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(tmp);
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyDocument(@RequestBody DocumentGenericRegisterDTO obj) throws GenericObjectServiceException, GenericProcedureException {
        ApiResponseObject<DocumentGenericBasicDTO> tmp = this.documentInvoiceUseCase.modifyDocument(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(tmp);
    }

    @PutMapping("/approved")
    public ResponseEntity<?> approvedDocument(@RequestParam(name = "numint", defaultValue = "0") Long numint) throws GenericProcedureException {
        ApiResponseObject<?> tmp = this.documentInvoiceUseCase.approvedDocument(numint);
        return ResponseEntity.ok(tmp);
    }

    @PutMapping("/on-account")
    public ResponseEntity<?> onAccountDocument(@RequestParam(name = "numint", defaultValue = "0") Long numint) throws GenericProcedureException {
        ApiResponseObject<?> tmp = this.documentInvoiceUseCase.onAccountDocument(numint);
        return ResponseEntity.ok(tmp);
    }

    @PutMapping("/cancel")
    public ResponseEntity<?> cancelDocument(@RequestParam(name = "numint", defaultValue = "0") Long numint,
                                            @RequestParam(name = "commen", defaultValue = "") String commen) throws GenericProcedureException {
        ApiResponseObject<?> tmp = this.documentInvoiceUseCase.cancelDocument(numint,commen);
        return ResponseEntity.ok(tmp);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> deleteDocument(@RequestParam(name = "numint", defaultValue = "0") Long numint,
                                            @RequestParam(name = "commen", defaultValue = "") String commen) throws GenericProcedureException {
        ApiResponseObject<?> tmp = this.documentInvoiceUseCase.deleteDocument(numint,commen);
        return ResponseEntity.ok(tmp);
    }

}
