package luis122448.SmartShell.application.domain.web.controller;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

import luis122448.SmartShell.application.domain.domain.model.ArticleDTO;
import luis122448.SmartShell.application.domain.domain.report.service.ArticleReport;
import luis122448.SmartShell.util.exception.GenericByteServiceException;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseByte;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ArticleService;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + "/article")
public class ArticleController {
	
	private final ArticleService articleService;
	private final ArticleReport articleReport;
	public ArticleController(ArticleService articleService, ArticleReport articleReport) {
		super();
		this.articleService = articleService;
		this.articleReport = articleReport;
	}

	@GetMapping("/by-export")
	public ResponseEntity<?> exportExcel(@RequestParam(value = "typinv", defaultValue = "0") Integer typinv) throws GenericListServiceException, GenericByteServiceException {
		ApiResponseByte<?> obj = this.articleReport.exportByExcel(typinv);
		return ResponseEntity.ok(obj);
	}

	@PostMapping("/by-import")
	public ResponseEntity<?> importExcel(@RequestParam(value = "typinv", defaultValue = "0") Integer typinv,
										 @RequestParam(name = "archive") MultipartFile multipartFile) throws GenericByteServiceException {
		ApiResponseByte<?> obj = this.articleReport.importByExcel(typinv,multipartFile);
		return ResponseEntity.ok(obj);
	}

	@GetMapping("/is-available")
	@Cacheable(value="shortTime")
	public ResponseEntity<?> isAvailable(@RequestParam("codart") String codart) throws GenericObjectServiceException {
		ArticleDTO dto = new ArticleDTO();
		dto.setCodart(codart);
		return ResponseEntity.ok(this.articleService.isAvailable(dto));
	}

	@GetMapping("/by-page")
	public ResponseEntity<?> findByPage (@RequestParam(name = "typinv", defaultValue = "1") Integer typinv,
										 @RequestParam(name = "codart", defaultValue = "") String codart,
										 @RequestParam(name = "descri", defaultValue = "") String descri,
										 @RequestParam(name = "status", defaultValue = "") String status,
										 Pageable p) throws GenericPageServiceException {
		ArticleDTO dto = new ArticleDTO();
		dto.setTypinv(typinv);
		dto.setCodart(codart);
		dto.setDescri(descri);
		dto.setStatus(status);
		return ResponseEntity.ok(this.articleService.findByPage(dto, p));
	}

	@GetMapping("/by-name")
	public ResponseEntity<?> findByName (@RequestParam(name = "name", defaultValue = "") String name) throws GenericListServiceException {
		return ResponseEntity.ok(this.articleService.findByName(name));
	}

	@GetMapping("/by-id")
	public ResponseEntity<?> findById (@RequestParam("codart") String codart) throws GenericObjectServiceException {
		ArticleDTO dto = new ArticleDTO();
		dto.setCodart(codart);
		return ResponseEntity.ok(this.articleService.findById(dto));
	}

	@PostMapping
	public ResponseEntity<?> save (@RequestBody ArticleDTO dto) throws GenericObjectServiceException {
		return ResponseEntity.ok(this.articleService.save(dto));
	}
	
	@PutMapping()
	public ResponseEntity<?> update (@RequestBody ArticleDTO dto) throws GenericObjectServiceException {
		return ResponseEntity.ok(articleService.update(dto));
	}
	
	@DeleteMapping()
	public ResponseEntity<?> delete (@RequestParam("codart") String codart) throws GenericObjectServiceException {
		ArticleDTO dto = new ArticleDTO();
		dto.setCodart(codart);
		return ResponseEntity.ok(articleService.delete(dto));
	}
	
}
