package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.model.FormatCommercialDocumentDTO;
import luis122448.SmartShell.application.domain.domain.service.service.FormatCommercialDocumentService;
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
    public ResponseEntity<?> findAll(@RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
        return ResponseEntity.ok(this.formatCommercialDocumentService.findAll(status));
    }

    @GetMapping("/by-typcomdoc")
    public ResponseEntity<?> findByTypcomdoc(@RequestParam(name = "typcomdoc") Integer typcomdoc,
                                        @RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
        return ResponseEntity.ok(this.formatCommercialDocumentService.findByTypcomdoc(typcomdoc, status));
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "typcomdoc") Integer typcomdoc,
                                      @RequestParam(name = "typformat") Integer typformat) throws GenericObjectServiceException {
        FormatCommercialDocumentDTO dto = new FormatCommercialDocumentDTO();
        dto.setTypcomdoc(typcomdoc);
        dto.setTypformat(typformat);
        return ResponseEntity.ok(this.formatCommercialDocumentService.findById(dto));
    }


}
