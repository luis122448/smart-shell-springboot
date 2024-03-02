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
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Optional;

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
    public ResponseEntity<?> printDocument(@RequestParam(name = "numint", defaultValue = "0") Long numint) throws GenericListServiceException, JRException {
        ApiResponseReport<?> tmp = this.documentInvoiceReport.invoicePrintDocument(numint);
        JasperPrint jasperPrint = tmp.getJasperPrint().orElseThrow();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();
        byte[] pdfBytes = outputStream.toByteArray();
        ApiResponseByte<?> obj = new ApiResponseByte<>(tmp.getStatus(),tmp.getMessage(), Optional.of(pdfBytes),"application/pdf");
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDocument(@RequestParam(name = "typcomdoc", defaultValue = "") Integer typcomdoc,
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
                                            @RequestParam(name = "codbuspar", defaultValue = "-1") String codbuspar) throws GenericListServiceException{
        DocumentInvoiceSearchFilterDTO obj = new DocumentInvoiceSearchFilterDTO(typcomdoc,startat,finalat,sitdomco,reacomdoc,serie,typpaycon,codbuspar);
        ApiResponseList<DocumentInvoiceSearchDTO> tmp = this.documentInvoiceUseCase.searchDocument(obj);
        return ResponseEntity.ok(tmp);
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageDocument(@RequestParam(name = "typcomdoc", defaultValue = "") Integer typcomdoc,
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
        DocumentInvoiceSearchFilterDTO obj = new DocumentInvoiceSearchFilterDTO(typcomdoc,startat,finalat,sitdomco,reacomdoc,serie,typpaycon,codbuspar);
        ApiResponsePage<DocumentInvoiceSearchDTO> tmp = this.documentInvoiceUseCase.pageDocument(obj, pageable);
        return ResponseEntity.ok(tmp);
    }

    @PostMapping("")
    public ResponseEntity<?> registerDocument(@RequestBody DocumentInvoiceRegisterDTO obj) throws GenericObjectServiceException, GenericProcedureException {
        ApiResponseObject<DocumentInvoiceBasicDTO> tmp = this.documentInvoiceUseCase.registerDocument(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(tmp);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelDocument(@RequestParam(name = "numint", defaultValue = "0") Long numint) throws GenericProcedureException {
        ApiResponseObject<?> tmp = this.documentInvoiceUseCase.cancelDocument(numint);
        return ResponseEntity.ok(tmp);
    }

}
