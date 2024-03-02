package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.service.service.TypeBusinessPartnerService;
import luis122448.SmartShell.application.domain.persistence.entity.TypeBusinessPartnerEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@RestController
@RequestMapping(PATH_BILLING + "/type-business-partner")
public class TypeBusinessPartnerController {

    private final TypeBusinessPartnerService typeBusinessPartnerService;

    public TypeBusinessPartnerController(TypeBusinessPartnerService typeBusinessPartnerService) {
        this.typeBusinessPartnerService = typeBusinessPartnerService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findAll() throws GenericListServiceException {
        ApiResponseList<TypeBusinessPartnerEntity> lst = this.typeBusinessPartnerService.findAll();
        return ResponseEntity.ok(lst);
    }
}
