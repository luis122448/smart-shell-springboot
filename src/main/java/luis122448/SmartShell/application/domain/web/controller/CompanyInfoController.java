package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.view.CompanyInfoService;
import luis122448.SmartShell.application.domain.persistence.entity.CompanyInfoEntity;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PATH_BILLING + "/company-info")
public class CompanyInfoController {
    private final CompanyInfoService companyInfoService;
    public CompanyInfoController(CompanyInfoService companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findAll() throws GenericListServiceException{
        ApiResponseList<CompanyInfoEntity> obj = this.companyInfoService.findAll();
        return ResponseEntity.ok(obj);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody CompanyInfoEntity companyInfoEntity) throws GenericObjectServiceException{
        ApiResponseObject<CompanyInfoEntity> obj = this.companyInfoService.update(companyInfoEntity);
        return ResponseEntity.ok(obj);
    }
}
