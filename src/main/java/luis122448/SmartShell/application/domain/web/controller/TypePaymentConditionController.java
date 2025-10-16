package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.TypePaymentConditionEntity;
import luis122448.SmartShell.application.domain.domain.service.service.TypePaymentConditionService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + "/type-payment-condition")
public class TypePaymentConditionController {
	
	private final TypePaymentConditionService typePaymentConditionService;
	
	public TypePaymentConditionController(TypePaymentConditionService typePaymentConditionService) {
		super();
		this.typePaymentConditionService = typePaymentConditionService;
	}

	@GetMapping("/by-codintcom")
	public ResponseEntity<?> findByCodbuspar (@RequestParam(name = "codbuspar", defaultValue = "") String codbuspar ) throws GenericListServiceException {
		ApiResponseList<TypePaymentConditionEntity> lst = typePaymentConditionService.findByCodbuspar(codbuspar);
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("/by-all")
	public ResponseEntity<?> findByAll () throws GenericListServiceException {
		ApiResponseList<TypePaymentConditionEntity> lst = typePaymentConditionService.findAll();
		return ResponseEntity.ok(lst);
	}

}
