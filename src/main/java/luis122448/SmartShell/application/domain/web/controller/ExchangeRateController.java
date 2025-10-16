package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import java.time.LocalDate;

import luis122448.SmartShell.application.domain.domain.model.ExchangeRateDTO;
import luis122448.SmartShell.util.exception.GenericListServiceException;
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
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ExchangeRateService;

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
		return ResponseEntity.ok(this.exchangeRateService.findByLike(startat, finalat, origen, destin));
	}

	@GetMapping("/by-id")
	public ResponseEntity<?> findById(@RequestParam(name = "registdate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate registdate,
									  @RequestParam(name="origen") String origen,
									  @RequestParam(name="destin") String destin) throws GenericObjectServiceException {
		ExchangeRateDTO dto = new ExchangeRateDTO();
		dto.setRegistdate(registdate);
		dto.setOrigen(origen);
		dto.setDestin(destin);
		return ResponseEntity.ok(this.exchangeRateService.findById(dto));
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ExchangeRateDTO dto) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.exchangeRateService.save(dto));
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam(name="registdate") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate registdate,
											@RequestParam(name="origen") String origen,
											@RequestParam(name="destin") String destin) throws GenericObjectServiceException {
		ExchangeRateDTO dto = new ExchangeRateDTO();
		dto.setRegistdate(registdate);
		dto.setOrigen(origen);
		dto.setDestin(destin);
		return ResponseEntity.ok(this.exchangeRateService.delete(dto));
	}
	
}
