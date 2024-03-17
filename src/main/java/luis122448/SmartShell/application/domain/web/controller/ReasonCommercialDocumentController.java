package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ReasonCommercialDocumentService;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;
@RestController
@RequestMapping( PATH_BILLING + "/reason-commercial-document")
public class ReasonCommercialDocumentController {

    public final ReasonCommercialDocumentService reasonCommercialDocumentService;

    public ReasonCommercialDocumentController(ReasonCommercialDocumentService reasonCommercialDocumentService) {
        this.reasonCommercialDocumentService = reasonCommercialDocumentService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findAll() throws GenericListServiceException{
        ApiResponseList<ReasonCommercialDocumentEntity> obj =  this.reasonCommercialDocumentService.findAll();
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/by-like")
    public ResponseEntity<?> findByLike(@RequestParam(name = "typcomdoc", defaultValue = "") Integer typcomdoc,
                                        @RequestParam(name = "ingsalcom", defaultValue = "") Integer ingsalcom) throws GenericListServiceException{
        ReasonCommercialDocumentEntity tmp = new ReasonCommercialDocumentEntity();
        tmp.setTypcomdoc(typcomdoc);
        tmp.setIngsalcom(ingsalcom);
        ApiResponseList<ReasonCommercialDocumentEntity> obj =  this.reasonCommercialDocumentService.findByTypcomdocAndIngsalcom(tmp);
        return ResponseEntity.ok(obj);
    }

}
