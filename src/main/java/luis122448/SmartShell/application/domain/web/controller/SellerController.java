package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping
	public ResponseEntity<?> findAll() throws GenericListServiceException {
		ApiResponseList<SellerEntity> lst= sellerService.findAll(null);
		return ResponseEntity.ok(lst);
	}

}
