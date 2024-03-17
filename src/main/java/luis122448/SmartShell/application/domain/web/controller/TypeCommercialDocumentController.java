package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.TypeCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.domain.service.service.TypeCommercialDocumentService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + "/type-commercial-document")
public class TypeCommercialDocumentController {
	
	private final TypeCommercialDocumentService typeCommercialDocumentService;
	public TypeCommercialDocumentController(TypeCommercialDocumentService typeCommercialDocumentService) {
		super();
		this.typeCommercialDocumentService = typeCommercialDocumentService;
	}

	@GetMapping("/by-all")
	public ResponseEntity<?> findAll() throws GenericListServiceException {
		ApiResponseList<TypeCommercialDocumentEntity> lst = this.typeCommercialDocumentService.findAll();
		return ResponseEntity.ok(lst);
	}
	
}
