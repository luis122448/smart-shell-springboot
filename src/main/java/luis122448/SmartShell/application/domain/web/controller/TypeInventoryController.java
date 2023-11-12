package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.TypeInventoryEntity;
import luis122448.SmartShell.application.domain.domain.service.service.TypeInventoryService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + "/type-inventory")
public class TypeInventoryController {
	
	private final TypeInventoryService typeInventoryService;
	public TypeInventoryController(TypeInventoryService typeInventoryService) {
		super();
		this.typeInventoryService = typeInventoryService;
	}

	@GetMapping("/by-all")
	public ResponseEntity<?> findAll() throws GenericListServiceException {
		ApiResponseList<TypeInventoryEntity> obj = this.typeInventoryService.findAll(null);
		return ResponseEntity.ok(obj);
	}
}
