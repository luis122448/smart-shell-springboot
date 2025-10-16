package luis122448.SmartShell.application.domain.web.controller;

import luis122448.SmartShell.application.domain.domain.service.service.DocumentTransactionService;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_DOCUMENT;

@RestController
@RequestMapping(PATH_BILLING + PATH_DOCUMENT + "/document-transaction")
public class DocumentTransactionController {
    private final DocumentTransactionService documentTransactionService;
    public DocumentTransactionController(DocumentTransactionService documentTransactionService) {
        this.documentTransactionService = documentTransactionService;
    }

    @GetMapping("/by-numint")
    public ResponseEntity<?> findByNumint(@RequestParam Long numint) throws GenericListServiceException {
        return ResponseEntity.ok(this.documentTransactionService.findByNumint(numint));
    }
}
