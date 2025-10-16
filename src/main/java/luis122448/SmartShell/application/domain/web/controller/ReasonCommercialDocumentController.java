package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.model.ReasonCommercialDocumentDTO;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ReasonCommercialDocumentService;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;
@RestController
@RequestMapping( PATH_BILLING + "/reason-commercial-document")
public class ReasonCommercialDocumentController {

    public final ReasonCommercialDocumentService reasonCommercialDocumentService;

    public ReasonCommercialDocumentController(ReasonCommercialDocumentService reasonCommercialDocumentService) {
        super();
        this.reasonCommercialDocumentService = reasonCommercialDocumentService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException{
        return ResponseEntity.ok(this.reasonCommercialDocumentService.findAll(status));
    }

    @GetMapping("/by-like")
    public ResponseEntity<?> findByTypcomdocAndInout(@RequestParam(name = "typcomdoc", defaultValue = "") Integer typcomdoc,
                                                     @RequestParam(name = "inout", defaultValue = "") Integer inout,
                                                     @RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException{
        return ResponseEntity.ok(this.reasonCommercialDocumentService.findByTypcomdocAndInout(typcomdoc, inout, status));
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "typcomdoc") Integer typcomdoc,
                                     @RequestParam(name = "inout") Integer inout,
                                     @RequestParam(name = "reacomdoc") Integer reacomdoc) throws GenericObjectServiceException {
        ReasonCommercialDocumentDTO dto = new ReasonCommercialDocumentDTO();
        dto.setTypcomdoc(typcomdoc);
        dto.setInout(inout);
        dto.setReacomdoc(reacomdoc);
        return ResponseEntity.ok(this.reasonCommercialDocumentService.findById(dto));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReasonCommercialDocumentDTO dto) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.reasonCommercialDocumentService.save(dto));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ReasonCommercialDocumentDTO dto) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.reasonCommercialDocumentService.update(dto));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(name = "typcomdoc") Integer typcomdoc,
                                    @RequestParam(name = "inout") Integer inout,
                                    @RequestParam(name = "reacomdoc") Integer reacomdoc) throws GenericObjectServiceException {
        ReasonCommercialDocumentDTO dto = new ReasonCommercialDocumentDTO();
        dto.setTypcomdoc(typcomdoc);
        dto.setInout(inout);
        dto.setReacomdoc(reacomdoc);
        return ResponseEntity.ok(this.reasonCommercialDocumentService.delete(dto));
    }

}
