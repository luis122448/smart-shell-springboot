package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BUSINESS_PARTNER;

import luis122448.SmartShell.application.domain.domain.model.BusparPaymentConditionDTO;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.BusparPaymentConditionService;
import luis122448.SmartShell.application.domain.domain.service.service.view.TypePaymentConditionViewService;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + PATH_BUSINESS_PARTNER + "/typpaycon")
public class BusparPaymentConditionController {
	
	private final BusparPaymentConditionService busparPaymentConditionService;
	private final TypePaymentConditionViewService typePaymentConditionViewService;

	public BusparPaymentConditionController(BusparPaymentConditionService busparPaymentConditionService, TypePaymentConditionViewService typePaymentConditionViewService) {
		this.busparPaymentConditionService = busparPaymentConditionService;
		this.typePaymentConditionViewService = typePaymentConditionViewService;
	}

	@GetMapping
	public ResponseEntity<?> findByCodbuspar ( @RequestParam(name = "codbuspar") String codbuspar,
											   @RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
		return ResponseEntity.ok(this.typePaymentConditionViewService.findByCodBuspar(codbuspar,status));
	}
	
	@PostMapping
	public ResponseEntity<?> save ( @RequestBody BusparPaymentConditionDTO dto ) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.busparPaymentConditionService.save(dto));
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete (@RequestParam(name = "codbuspar") String codbuspar,
									 @RequestParam(name = "typpaycon") Short typpaycon ) throws GenericObjectServiceException {
		BusparPaymentConditionDTO dto = new BusparPaymentConditionDTO();
		dto.setCodbuspar(codbuspar);
		dto.setTyppaycon(typpaycon);
		return ResponseEntity.ok(this.busparPaymentConditionService.delete(dto));
	}
	
}
