package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BUSINESS_PARTNER;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.util.exception.GenericListServiceException;
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
import luis122448.SmartShell.application.domain.persistence.entity.BusinessPartnerEntity;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.BusinessPartnerService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
@Slf4j
@RestController
@RequestMapping(PATH_BILLING + PATH_BUSINESS_PARTNER)
public class BusinessPartnerController {
	
	private final BusinessPartnerService businessPartnerService;
	public BusinessPartnerController(BusinessPartnerService businessPartnerService) {
		super();
		this.businessPartnerService = businessPartnerService;
	}
	
	@GetMapping("/{codbuspar}")
	public ResponseEntity<?> findById (@PathVariable("codbuspar") String codbuspar) throws GenericObjectServiceException {
		BusinessPartnerEntity ori = new BusinessPartnerEntity();
		ori.setCodbuspar(codbuspar);
		ApiResponseObject<BusinessPartnerEntity> lst = this.businessPartnerService.findById(ori);
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("/by-like")
	public ResponseEntity<?> findByLike (@RequestParam(name = "typbuspar", defaultValue = "") Short typbuspar,
										 @RequestParam(name = "codbuspar", defaultValue = "") String codbuspar,
										 @RequestParam(name = "busnam", defaultValue = "") String busnam,
										 @RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
		BusinessPartnerEntity ori = new BusinessPartnerEntity();
		ori.setTypbuspar(typbuspar);
		ori.setCodbuspar(codbuspar);
		ori.setBusnam(busnam);
		ori.setStatus(status);
		ApiResponseList<BusinessPartnerEntity> lst = this.businessPartnerService.findByLike(ori);
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping("/by-page")
	public ResponseEntity<?> findByPage (@RequestParam(name = "typbuspar", defaultValue = "") Short typbuspar,
										 @RequestParam(name = "codbuspar", defaultValue = "") String codbuspar,
										 @RequestParam(name = "busnam", defaultValue = "") String busnam,
										 @RequestParam(name = "status", defaultValue = "") String status,
										 Pageable p) throws GenericPageServiceException {
		BusinessPartnerEntity ori = new BusinessPartnerEntity();
		ori.setTypbuspar(typbuspar);
		ori.setCodbuspar(codbuspar);
		ori.setBusnam(busnam);
		ori.setStatus(status);
		ApiResponsePage<BusinessPartnerEntity> lst = this.businessPartnerService.findByPage(ori, p);
		return ResponseEntity.ok(lst);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody BusinessPartnerEntity t)  throws GenericObjectServiceException {
		ApiResponseObject<BusinessPartnerEntity> lst = this.businessPartnerService.save(t);
		return ResponseEntity.ok(lst);
	}
	
	@PutMapping("/{codbuspar}")
	public ResponseEntity<ApiResponseObject<BusinessPartnerEntity>> update(@PathVariable("codbuspar") String codbuspar, @RequestBody BusinessPartnerEntity t) throws GenericObjectServiceException {
		t.setCodbuspar(codbuspar);
		ApiResponseObject<BusinessPartnerEntity> obj = this.businessPartnerService.update(t);
		return ResponseEntity.ok(obj);
	}
	
	@PutMapping("/undelete/{codbuspar}")
	public ResponseEntity<ApiResponseObject<BusinessPartnerEntity>> undelete(@PathVariable("codbuspar") String codbuspar) throws GenericObjectServiceException {
		BusinessPartnerEntity tmp = new BusinessPartnerEntity();
		tmp.setCodbuspar(codbuspar);
		ApiResponseObject<BusinessPartnerEntity> obj = this.businessPartnerService.undelete(tmp);
		return ResponseEntity.ok(obj);
	}
	
	@DeleteMapping("/{codbuspar}")
	public ResponseEntity<?> delete(@PathVariable("codbuspar") String codbuspar ) throws GenericObjectServiceException {
		BusinessPartnerEntity tmp = new BusinessPartnerEntity();
		tmp.setCodbuspar(codbuspar);
		ApiResponseObject<BusinessPartnerEntity> obj = this.businessPartnerService.delete(tmp);
		return ResponseEntity.ok(obj);
	}
}
