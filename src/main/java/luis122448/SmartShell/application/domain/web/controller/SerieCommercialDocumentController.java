package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.application.domain.persistence.entity.key.SerieCommercialDocumentPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/by-all")
	public ResponseEntity<?> findAll () throws GenericListServiceException {
		ApiResponseList<SerieCommercialDocumentEntity> lst = this.serieCommercialDocumentService.findAll();
		return ResponseEntity.ok(lst);
	}

	@GetMapping("/by-like")
	public ResponseEntity<?> findByLike (@RequestParam(name = "typcomdoc", defaultValue = "") Integer typcomdoc) throws GenericListServiceException {
		SerieCommercialDocumentEntity tmp = new SerieCommercialDocumentEntity();
		tmp.setTypcomdoc(typcomdoc);
		ApiResponseList<SerieCommercialDocumentEntity> lst = this.serieCommercialDocumentService.findByLike(tmp);
		return ResponseEntity.ok(lst);
	}

	@GetMapping("/by-id")
	public ResponseEntity<?> findById (@RequestParam(name = "typcomdoc", defaultValue = "") Integer typcomdoc,
										@RequestParam(name = "serie", defaultValue = "") String serie) throws GenericObjectServiceException {
		SerieCommercialDocumentPK tmp = new SerieCommercialDocumentPK();
		tmp.setTypcomdoc(typcomdoc);
		tmp.setSerie(serie);
		ApiResponseObject<SerieCommercialDocumentEntity> obj = this.serieCommercialDocumentService.findById(tmp);
		return ResponseEntity.ok(obj);
	}

	@PostMapping
	public ResponseEntity<?> save (@RequestBody SerieCommercialDocumentEntity t) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.serieCommercialDocumentService.save(t));
	}

	@PutMapping
	public ResponseEntity<?> update (@RequestBody SerieCommercialDocumentEntity t) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.serieCommercialDocumentService.update(t));
	}

	@DeleteMapping
	public ResponseEntity<?> delete (@RequestBody SerieCommercialDocumentPK t) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.serieCommercialDocumentService.delete(t));
	}

}
