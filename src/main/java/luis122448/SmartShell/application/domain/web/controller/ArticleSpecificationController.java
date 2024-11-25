package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.service.service.ArticleSpecificationService;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@RestController
@RequestMapping(PATH_BILLING + "/article-specification")
public class ArticleSpecificationController {
    private final ArticleSpecificationService articleSpecificationService;

    public ArticleSpecificationController(ArticleSpecificationService articleSpecificationService) {
        this.articleSpecificationService = articleSpecificationService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findByAll(@RequestParam(value = "typinv") Integer typinv,
                                       @RequestParam(value = "status") String status) throws GenericListServiceException{
        return ResponseEntity.ok(this.articleSpecificationService.findByTypinv(typinv,status));
    }

}
