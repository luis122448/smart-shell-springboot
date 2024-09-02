package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_DOCUMENT;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.domain.model.DocumentGenericSearchFilterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentHeaderService;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + PATH_DOCUMENT +"/header")
public class DocumentHeaderController {
	
	private final DocumentHeaderService documentHeaderService;
	public DocumentHeaderController(DocumentHeaderService documentHeaderService) {
		super();
		this.documentHeaderService = documentHeaderService;
	}

	@GetMapping("/by-id")
	public ResponseEntity<?> findById (@RequestParam(name = "numint", defaultValue = "") Long numint ) throws GenericObjectServiceException {
		DocumentHeaderEntity tmp = new DocumentHeaderEntity();
		tmp.setNumint(numint);
		ApiResponseObject<DocumentHeaderEntity> obj = this.documentHeaderService.findByNumint(numint);
		return ResponseEntity.ok(obj);
	}

	@GetMapping("/by-summary")
	public ResponseEntity<?> findDocumentHeaderSummary(@RequestBody DocumentGenericSearchFilterDTO t) throws GenericListServiceException {
		return ResponseEntity.ok(this.documentHeaderService.searchDocumentGeneric(t));
	}

	@PostMapping()
	public ResponseEntity<?> registerDocumentHeader(@RequestBody DocumentHeaderEntity obj) throws GenericObjectServiceException {
		ApiResponseObject<DocumentHeaderEntity> tmp = this.documentHeaderService.registerDocumentHeader(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(tmp);
	}

	
}
