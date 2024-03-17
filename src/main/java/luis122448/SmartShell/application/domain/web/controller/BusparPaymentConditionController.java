package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BUSINESS_PARTNER;

import luis122448.SmartShell.application.domain.persistence.entity.key.BusparPaymentConditionPK;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.BusparPaymentConditionEntity;
import luis122448.SmartShell.application.domain.persistence.view.TypePaymentConditionViewEntity;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.BusparPaymentConditionService;
import luis122448.SmartShell.application.domain.domain.service.service.view.TipoCondicionPagoViewService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + PATH_BUSINESS_PARTNER + "/typpaycon")
public class BusparPaymentConditionController {
	
	private final BusparPaymentConditionService intcomCondicionPago;
	private final TipoCondicionPagoViewService tipoCondicionPagoViewService;
	public BusparPaymentConditionController(BusparPaymentConditionService intcomCondicionPago,
											TipoCondicionPagoViewService tipoCondicionPagoViewService) {
		super();
		this.intcomCondicionPago = intcomCondicionPago;
		this.tipoCondicionPagoViewService = tipoCondicionPagoViewService;
	}

	@GetMapping
	public ResponseEntity<?> findByCodbuspar ( @RequestParam(name = "codbuspar", defaultValue = "") String codbuspar ) throws GenericObjectServiceException {
		TypePaymentConditionViewEntity tmp = new TypePaymentConditionViewEntity();
		tmp.setCodbuspar(codbuspar);
		ApiResponseList<TypePaymentConditionViewEntity> lst = this.tipoCondicionPagoViewService.findByLike(tmp);
		return ResponseEntity.ok(lst);
	}
	
	@PostMapping
	public ResponseEntity<?> save ( @RequestBody BusparPaymentConditionEntity t ) throws GenericObjectServiceException {
		ApiResponseObject<BusparPaymentConditionEntity> lst = this.intcomCondicionPago.save(t);
		return ResponseEntity.ok(lst);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete (@RequestParam(name = "codbuspar", defaultValue = "") String codbuspar, @RequestParam(name = "typpaycon", defaultValue = "") Short typpaycon ) throws GenericObjectServiceException {
		BusparPaymentConditionPK tmp = new BusparPaymentConditionPK(0, codbuspar, typpaycon);
		ApiResponseObject<BusparPaymentConditionEntity> lst = this.intcomCondicionPago.delete(tmp);
		return ResponseEntity.ok(lst);
	}
	
}
