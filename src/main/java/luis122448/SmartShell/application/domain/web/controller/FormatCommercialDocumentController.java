package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.service.service.FormatCommercialDocumentService;
import luis122448.SmartShell.application.domain.persistence.entity.FormatCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.FormatCommercialDocumentPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@RestController
@RequestMapping(PATH_BILLING + "/format-commercial-document")
public class FormatCommercialDocumentController {

    private final FormatCommercialDocumentService formatCommercialDocumentService;

    public FormatCommercialDocumentController(FormatCommercialDocumentService formatCommercialDocumentService) {
        this.formatCommercialDocumentService = formatCommercialDocumentService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findAll() throws GenericListServiceException {
        return ResponseEntity.ok(this.formatCommercialDocumentService.findAll());
    }

    @GetMapping("/by-like")
    public ResponseEntity<?> findByLike(@RequestParam(name = "typcomdoc", defaultValue = "0") Integer typcomdoc) throws GenericListServiceException {
        FormatCommercialDocumentEntity tmp = new FormatCommercialDocumentEntity();
        tmp.setTypcomdoc(typcomdoc);
        return ResponseEntity.ok(this.formatCommercialDocumentService.findByLike(tmp));
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "typcomdoc", defaultValue = "0") Integer typcomdoc,
                                      @RequestParam(name = "typformat", defaultValue = "0") Integer typformat) throws GenericObjectServiceException {
        FormatCommercialDocumentPK tmp = new FormatCommercialDocumentPK();
        tmp.setTypcomdoc(typcomdoc);
        tmp.setTypformat(typformat);
        return ResponseEntity.ok(this.formatCommercialDocumentService.findById(tmp));
    }


}
