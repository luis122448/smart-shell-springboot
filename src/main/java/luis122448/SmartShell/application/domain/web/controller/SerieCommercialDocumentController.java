package luis122448.SmartShell.application.domain.web.controller;

import static java.util.Objects.isNull;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.SerieCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.domain.service.service.SerieCommercialDocumentService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + "/serie-commercial-document")
public class SerieCommercialDocumentController {

	private final SerieCommercialDocumentService serieCommercialDocumentService;

	public SerieCommercialDocumentController(SerieCommercialDocumentService serieCommercialDocumentService) {
		super();
		this.serieCommercialDocumentService = serieCommercialDocumentService;
	}

	@GetMapping()
	public ResponseEntity<?> findAll () throws GenericListServiceException {
		SerieCommercialDocumentEntity tmp = new SerieCommercialDocumentEntity();
		ApiResponseList<SerieCommercialDocumentEntity> lst = this.serieCommercialDocumentService.findAll(tmp);
		if (isNull(lst)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lst);
	}

	@GetMapping("/by-like")
	public ResponseEntity<?> findByLike (@RequestParam(name = "typcomdoc", defaultValue = "") Integer typcomdoc) throws GenericListServiceException {
		SerieCommercialDocumentEntity tmp = new SerieCommercialDocumentEntity();
		tmp.setTypcomdoc(typcomdoc);
		ApiResponseList<SerieCommercialDocumentEntity> lst = this.serieCommercialDocumentService.findByLike(tmp);
		if (isNull(lst)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lst);
	}

}
