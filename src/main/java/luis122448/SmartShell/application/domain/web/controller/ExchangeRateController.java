package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import java.time.LocalDate;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.ExchangeRateEntity;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ExchangeRateService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + "/exchange-rate")
public class ExchangeRateController {
	
	private final ExchangeRateService exchangeRateService;

	public ExchangeRateController(ExchangeRateService exchangeRateService) {
		super();
		this.exchangeRateService = exchangeRateService;
	}
	
	@GetMapping("/by-like")
	public ResponseEntity<?> findByLike(@RequestParam(name="startat", defaultValue="") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startat,
												   @RequestParam(name="finalat", defaultValue="") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate finalat,
												   @RequestParam(name="origen", defaultValue="") String origen,
												   @RequestParam(name="destin", defaultValue="") String destin) throws GenericListServiceException {
		ApiResponseList<ExchangeRateEntity> lst = this.exchangeRateService.findByLike(startat, finalat, origen, destin);
		return ResponseEntity.ok(lst);
	}

	@GetMapping("/by-id")
	public ResponseEntity<?> findById(@RequestParam(name = "registdate", defaultValue = "") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate registdate,
									  @RequestParam(name="origen", defaultValue="") String origen,
									  @RequestParam(name="destin", defaultValue="") String destin) throws GenericObjectServiceException {
		ExchangeRateEntity tmp = new ExchangeRateEntity();
		tmp.setRegistdate(registdate);
		tmp.setOrigen(origen);
		tmp.setDestin(destin);
		ApiResponseObject<ExchangeRateEntity> obj = this.exchangeRateService.findById(tmp);
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ExchangeRateEntity t) throws GenericObjectServiceException {
		ApiResponseObject<ExchangeRateEntity> obj =  this.exchangeRateService.save(t);
		return ResponseEntity.ok(obj);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam(name="registdate", defaultValue="") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate registdate,
											@RequestParam(name="origen", defaultValue="") String origen,
											@RequestParam(name="destin", defaultValue="") String destin) throws GenericObjectServiceException {
		ExchangeRateEntity tmp = new ExchangeRateEntity();
		tmp.setRegistdate(registdate);
		tmp.setOrigen(origen);
		tmp.setDestin(destin);
		ApiResponseObject<ExchangeRateEntity> obj =  this.exchangeRateService.delete(tmp);
		return ResponseEntity.ok(obj);
	}
	
}
