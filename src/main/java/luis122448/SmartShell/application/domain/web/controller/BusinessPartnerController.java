package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BUSINESS_PARTNER;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.application.domain.domain.model.BusinessPartnerDTO;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.BusinessPartnerService;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + PATH_BUSINESS_PARTNER)
public class BusinessPartnerController {
	
	private final BusinessPartnerService businessPartnerService;
	public BusinessPartnerController(BusinessPartnerService businessPartnerService) {
		super();
		this.businessPartnerService = businessPartnerService;
	}

	@GetMapping("/by-page")
	public ResponseEntity<?> findByPage (@RequestParam(name = "typbuspar", defaultValue = "") Short typbuspar,
										 @RequestParam(name = "codbuspar", defaultValue = "") String codbuspar,
										 @RequestParam(name = "busnam", defaultValue = "") String busnam,
										 @RequestParam(name = "status", defaultValue = "") String status,
										 Pageable p) throws GenericPageServiceException {
		BusinessPartnerDTO dto = new BusinessPartnerDTO();
		dto.setTypbuspar(typbuspar);
		dto.setCodbuspar(codbuspar);
		dto.setBusnam(busnam);
		dto.setStatus(status);
		return ResponseEntity.ok(this.businessPartnerService.findByPage(dto, p));
	}

	@GetMapping("/{codbuspar}")
	public ResponseEntity<?> findById (@PathVariable("codbuspar") String codbuspar) throws GenericObjectServiceException {
		BusinessPartnerDTO dto = new BusinessPartnerDTO();
		dto.setCodbuspar(codbuspar);
		return ResponseEntity.ok(this.businessPartnerService.findById(dto));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody BusinessPartnerDTO dto)  throws GenericObjectServiceException {
		return ResponseEntity.ok(this.businessPartnerService.save(dto));
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody BusinessPartnerDTO dto) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.businessPartnerService.update(dto));
	}

	@DeleteMapping()
	public ResponseEntity<?> delete(@RequestParam("codbuspar") String codbuspar) throws GenericObjectServiceException {
		BusinessPartnerDTO dto = new BusinessPartnerDTO();
		dto.setCodbuspar(codbuspar);
		return ResponseEntity.ok(dto);
	}
}
