package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.application.domain.domain.model.SerieCommercialDocumentDTO;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.service.service.SerieCommercialDocumentService;

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
	public ResponseEntity<?> findAll (@RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
		return ResponseEntity.ok(this.serieCommercialDocumentService.findAll(status));
	}

	@GetMapping("/by-typcomdoc")
	public ResponseEntity<?> findByTypcomdoc (@RequestParam(name = "typcomdoc") Integer typcomdoc,
											  @RequestParam(name = "status", defaultValue = "") String status) throws GenericListServiceException {
		return ResponseEntity.ok(this.serieCommercialDocumentService.findByTypcomdoc(typcomdoc,status));
	}

	@GetMapping("/by-id")
	public ResponseEntity<?> findById (@RequestParam(name = "typcomdoc") Integer typcomdoc,
										@RequestParam(name = "serie") String serie) throws GenericObjectServiceException {
		SerieCommercialDocumentDTO dto = new SerieCommercialDocumentDTO();
		dto.setTypcomdoc(typcomdoc);
		dto.setSerie(serie);
		ApiResponseObject<?> obj = this.serieCommercialDocumentService.findById(dto);
		return ResponseEntity.ok(obj);
	}

	@PostMapping
	public ResponseEntity<?> save (@RequestBody SerieCommercialDocumentDTO dto) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.serieCommercialDocumentService.save(dto));
	}

	@PutMapping
	public ResponseEntity<?> update (@RequestBody SerieCommercialDocumentDTO dto) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.serieCommercialDocumentService.update(dto));
	}

	@DeleteMapping
	public ResponseEntity<?> delete (@RequestParam("typcomdoc") Integer typcomdoc,
									 @RequestParam("serie") String serie) throws GenericObjectServiceException {
		SerieCommercialDocumentDTO dto = new SerieCommercialDocumentDTO();
		dto.setTypcomdoc(typcomdoc);
		dto.setSerie(serie);
		return ResponseEntity.ok(this.serieCommercialDocumentService.delete(dto));
	}

}
