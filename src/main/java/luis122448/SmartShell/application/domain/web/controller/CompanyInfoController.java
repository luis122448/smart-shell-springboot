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
        CompanyInfoEntity tmp = new CompanyInfoEntity();
        ApiResponseList<CompanyInfoEntity> obj = this.companyInfoService.findAll(tmp);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam(name = "numint", defaultValue = "1") Integer numint) throws GenericObjectServiceException{
        CompanyInfoEntity tmp = new CompanyInfoEntity();
        tmp.setNumint(numint);
        ApiResponseObject<CompanyInfoEntity> obj = this.companyInfoService.findById(tmp);
        return ResponseEntity.ok(obj);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestParam(name = "numint", defaultValue = "1") Integer numint,
                                    @RequestBody CompanyInfoEntity companyInfoEntity) throws GenericObjectServiceException{
        companyInfoEntity.setNumint(numint);
        ApiResponseObject<CompanyInfoEntity> obj = this.companyInfoService.update(companyInfoEntity);
        return ResponseEntity.ok(obj);
    }
}
