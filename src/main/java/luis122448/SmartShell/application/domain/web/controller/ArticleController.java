package luis122448.SmartShell.application.domain.web.controller;

import static java.util.Objects.isNull;
import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ArticleService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
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
		log.info("import archive");
		log.info("typinv {}", typinv);
		ApiResponseByte<?> obj = this.articleReport.importByExcel(typinv,multipartFile);
		return ResponseEntity.ok(obj);
	}

	@GetMapping("/is-available")
	@Cacheable(value="shortTime")
	public ResponseEntity<?> isAvailable(@RequestParam("codart") String codart) throws GenericObjectServiceException {
		ArticleEntity tmp = new ArticleEntity();
		tmp.setCodart(codart);
		return ResponseEntity.ok(this.articleService.isAvailable(tmp));
	}
	
	@GetMapping("/{codart}")
	public ResponseEntity<?> findById (@PathVariable("codart") String codart) throws GenericObjectServiceException {
		ArticleEntity tmp = new ArticleEntity();
		tmp.setCodart(codart);
		ApiResponseObject<ArticleEntity> obj = this.articleService.findById(tmp);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/by-like")
	public ResponseEntity<?> findByLike (@RequestParam(name = "codart", defaultValue = "") String codart,
			@RequestParam(name = "descri", defaultValue = "") String descri) throws GenericListServiceException {
		ArticleEntity ori = new ArticleEntity();
		ori.setCodart(codart);
		ori.setDescri(descri);
		ApiResponseList<ArticleEntity> lst = this.articleService.findByLike(ori);
		return ResponseEntity.ok(lst);
	}

	@GetMapping("/by-name")
	public ResponseEntity<?> findByName (@RequestParam(name = "name", defaultValue = "") String name) throws GenericListServiceException {
		ApiResponseList<ArticleEntity> lst = this.articleService.findByName(name);
		return ResponseEntity.ok(lst);
	}

	@GetMapping("/by-page")
	public ResponseEntity<?> findByPage (@RequestParam(name = "codart", defaultValue = "") String codart,
			@RequestParam(name = "descri", defaultValue = "") String descri, Pageable p) throws GenericPageServiceException {
		ArticleEntity ori = new ArticleEntity();
		ori.setCodart(codart);
		ori.setDescri(descri);
		ApiResponsePage<ArticleEntity> lst = this.articleService.findByPage(ori, p);
		return ResponseEntity.ok(lst);
	}
	
	@PostMapping
	public ResponseEntity<?> save (@RequestBody ArticleEntity t) throws GenericObjectServiceException {
		try {
			ApiResponseObject<ArticleEntity> obj = this.articleService.save(t);
			if (isNull(obj)) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping("/{codart}")
	public ResponseEntity<?> update (@PathVariable("codart") String codart, @RequestBody ArticleEntity t) throws GenericObjectServiceException {
		t.setCodart(codart);
		ApiResponseObject<ArticleEntity> obj = articleService.update(t);
		return ResponseEntity.ok(obj);
	}
	
	@DeleteMapping("/{codart}")
	public ResponseEntity<?> delete (@PathVariable("codart") String codart) throws GenericObjectServiceException {
		ArticleEntity tmp = new ArticleEntity();
		tmp.setCodart(codart);
		ApiResponseObject<ArticleEntity> obj = articleService.delete(tmp);
		return ResponseEntity.ok(obj);
	}
	
	@PutMapping("/undelete/{codart}")
	public ResponseEntity<?> undelete (@PathVariable("codart") String codart) throws GenericObjectServiceException {
		ArticleEntity tmp = new ArticleEntity();
		tmp.setCodart(codart);
		ApiResponseObject<ArticleEntity> obj = articleService.undelete(tmp);
		return ResponseEntity.ok(obj);
	}
	
}
