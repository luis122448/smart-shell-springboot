package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.model.DocumentGenericBasicDTO;
import luis122448.SmartShell.application.domain.domain.model.DocumentGenericRegisterDTO;
import luis122448.SmartShell.application.domain.domain.model.DocumentGenericSearchDTO;
import luis122448.SmartShell.application.domain.domain.report.service.DocumentInternalGuideReport;
import luis122448.SmartShell.application.domain.domain.usecase.DocumentInternalGuideUseCase;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseByte;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_DOCUMENT;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_INVENTORY;

@RestController
@RequestMapping(PATH_INVENTORY + PATH_DOCUMENT + "/internal-guide")
public class DocumentInternalGuideController {

    private final DocumentInternalGuideReport documentInternalGuideReport;
    private final DocumentInternalGuideUseCase documentInternalGuideUseCase;

    public DocumentInternalGuideController(DocumentInternalGuideReport documentInternalGuideReport, DocumentInternalGuideUseCase documentInternalGuideUseCase) {
        this.documentInternalGuideReport = documentInternalGuideReport;
        this.documentInternalGuideUseCase = documentInternalGuideUseCase;
    }

    @GetMapping("/print")
    public ResponseEntity<?> printDocument(@RequestParam(name = "numint", defaultValue = "0") Long numint) throws GenericListServiceException, JRException, FileNotFoundException {
        ApiResponseByte<?> obj = new ApiResponseByte<>(this.documentInternalGuideReport.printDocument(numint),"application/pdf");
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/search-page")
    public ResponseEntity<?> searchPageDocument(@RequestParam(name = "typcomdoc", defaultValue = "") Integer typcomdoc,
                                                @RequestParam(name = "startat", defaultValue = "") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startat,
                                                @RequestParam(name = "finalat", defaultValue = "") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate finalat,
                                                @RequestParam(name = "sitcomdoc", defaultValue = "-1") String sitcomdoc,
                                                Pageable pageable) throws GenericPageServiceException {
        ApiResponsePage<DocumentGenericSearchDTO> tmp = this.documentInternalGuideUseCase.searchPageDocument(typcomdoc,startat,finalat,sitcomdoc,pageable);
        return ResponseEntity.ok(tmp);
    }

    @GetMapping("/by-numint")
    public ResponseEntity<?> findByNumint(@RequestParam(name = "numint", defaultValue = "0") Long numint) throws GenericObjectServiceException, GenericListServiceException {
        return ResponseEntity.ok(this.documentInternalGuideUseCase.findByNumint(numint));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerDocument(@RequestBody DocumentGenericRegisterDTO obj) throws GenericObjectServiceException, GenericProcedureException {
        ApiResponseObject<DocumentGenericBasicDTO> tmp = this.documentInternalGuideUseCase.registerDocument(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(tmp);
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyDocument(@RequestBody DocumentGenericRegisterDTO obj) throws GenericObjectServiceException, GenericProcedureException {
        ApiResponseObject<DocumentGenericBasicDTO> tmp = this.documentInternalGuideUseCase.modifyDocument(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(tmp);
    }

    @PutMapping("/sent-kardex")
    public ResponseEntity<?> sentDocumentKardex(@RequestParam(name = "numint", defaultValue = "0") Long numint) throws GenericProcedureException {
        ApiResponseObject<?> tmp = this.documentInternalGuideUseCase.sentDocumentKardex(numint);
        return ResponseEntity.ok(tmp);
    }

    @PutMapping("/cancel")
    public ResponseEntity<?> cancelDocument(@RequestParam(name = "numint", defaultValue = "0") Long numint, @RequestParam(name = "commen", defaultValue = "") String commen) throws GenericProcedureException {
        ApiResponseObject<?> tmp = this.documentInternalGuideUseCase.cancelDocument(numint, commen);
        return ResponseEntity.ok(tmp);
    }

}
