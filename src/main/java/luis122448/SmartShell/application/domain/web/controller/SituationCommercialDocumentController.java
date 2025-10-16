package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.SituationCommercialDocumentService;
import luis122448.SmartShell.application.domain.persistence.entity.SituationCommercialDocumentEntity;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@RestController
@RequestMapping(PATH_BILLING + "/situation-commercial-document")
public class SituationCommercialDocumentController {

    private final SituationCommercialDocumentService situationCommercialDocumentService;
    public SituationCommercialDocumentController(SituationCommercialDocumentService situationCommercialDocumentService) {
        this.situationCommercialDocumentService = situationCommercialDocumentService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findAll(@RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
        ApiResponseList<SituationCommercialDocumentEntity> lst = this.situationCommercialDocumentService.findAll(status);
        return ResponseEntity.ok(lst);
    }

    @GetMapping("/by-like")
    public ResponseEntity<?> findByLike(@RequestParam(name = "typcomdoc") Integer typcomdoc,
                                        @RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException{
        ApiResponseList<SituationCommercialDocumentEntity> obj = this.situationCommercialDocumentService.findByTypcomdoc(typcomdoc,status);
        return ResponseEntity.ok(obj);
    }
}
