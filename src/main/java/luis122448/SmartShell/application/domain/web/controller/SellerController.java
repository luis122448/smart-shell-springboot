package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.application.domain.domain.model.SellerDTO;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.SellerEntity;
import luis122448.SmartShell.application.domain.domain.service.service.SellerService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + "/seller")
public class SellerController {

	private final SellerService sellerService;
	
	public SellerController(SellerService sellerService) {
		super();
		this.sellerService = sellerService;
	}

	@GetMapping("/by-all")
	public ResponseEntity<?> findAll(@RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
		ApiResponseList<SellerEntity> lst = sellerService.findAll(status);
		return ResponseEntity.ok(lst);
	}

	@GetMapping("/by-id")
	public ResponseEntity<?> findById(@RequestParam(name = "codsel", defaultValue = "") String codsel) throws GenericObjectServiceException {
		SellerDTO dto = new SellerDTO();
		dto.setCodsel(codsel);
		return ResponseEntity.ok(this.sellerService.findById(dto));
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody SellerDTO dto) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.sellerService.save(dto));
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody SellerDTO dto) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.sellerService.update(dto));
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam("codsel") String codsel) throws GenericObjectServiceException {
		SellerDTO dto = new SellerDTO();
		dto.setCodsel(codsel);
		return ResponseEntity.ok(this.sellerService.delete(dto));
	}

}
