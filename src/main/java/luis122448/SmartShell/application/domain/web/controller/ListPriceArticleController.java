package luis122448.SmartShell.application.domain.web.controller;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.report.service.ListPriceArticleReport;
import luis122448.SmartShell.util.exception.GenericByteServiceException;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ListPriceArticleService;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceArticleEntity;
import luis122448.SmartShell.util.object.api.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;
@Slf4j
@RestController
@RequestMapping(PATH_BILLING + "/list-price-article")
public class ListPriceArticleController {
    private final ListPriceArticleService listPriceArticleService;
    private final ListPriceArticleReport listPriceArticleReport;

    public ListPriceArticleController(ListPriceArticleService listPriceArticleService, ListPriceArticleReport listPriceArticleReport) {
        this.listPriceArticleService = listPriceArticleService;
        this.listPriceArticleReport = listPriceArticleReport;
    }

    @GetMapping("/by-export")
    public ResponseEntity<?> exportExcel(@RequestParam(value = "codlistprice", defaultValue = "0") Integer codlistprice) throws GenericListServiceException, GenericByteServiceException {
        ApiResponseByte<?> obj = this.listPriceArticleReport.exportByExcel(codlistprice);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/by-generate")
    public ResponseEntity<?> generateExcel(@RequestParam(value = "codlistprice", defaultValue = "0") Integer codlistprice) throws GenericListServiceException, GenericByteServiceException {
        ApiResponseByte<?> obj = this.listPriceArticleReport.generateByExcel(codlistprice);
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/by-import")
    public ResponseEntity<?> importExcel(@RequestParam(value = "codlistprice", defaultValue = "0") Integer codlistprice,
                                         @RequestParam(name = "archive") MultipartFile multipartFile) throws GenericByteServiceException {
        log.info("import archive");
        ApiResponseByte<?> obj = this.listPriceArticleReport.importByExcel(codlistprice,multipartFile);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/by-page")
    public ResponseEntity<?> findByPage(@RequestParam(value = "codlistprice", defaultValue = "0") Integer codlistprice,
                                        @RequestParam(value = "codart", defaultValue = "") String codart,
                                        @RequestParam(value = "desart", defaultValue = "") String desart,
                                        Pageable pageable) throws GenericPageServiceException {
        ListPriceArticleEntity tmp = new ListPriceArticleEntity();
        tmp.setCodlistprice(codlistprice);
        tmp.setCodart(codart);
        tmp.setDesart(desart);
        ApiResponsePage<ListPriceArticleEntity> obj = this.listPriceArticleService.findByPage(tmp,pageable);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findAll(@RequestParam(value = "codlistprice", defaultValue = "0") Integer codlistprice) throws GenericListServiceException {
        ListPriceArticleEntity tmp = new ListPriceArticleEntity();
        tmp.setCodlistprice(codlistprice);
        ApiResponseList<ListPriceArticleEntity> obj = this.listPriceArticleService.findAll(tmp);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/by-like")
    public ResponseEntity<?> findByLike(@RequestParam(value = "codart", defaultValue = "") String codart) throws GenericListServiceException {
        ListPriceArticleEntity tmp = new ListPriceArticleEntity();
        tmp.setCodart(codart);
        ApiResponseList<ListPriceArticleEntity> obj = this.listPriceArticleService.findByLike(tmp);
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam(value = "codlistprice", defaultValue = "0") Integer codlistprice,
                                     @RequestParam(value = "codart", defaultValue = "") String codart) throws GenericObjectServiceException {
        ListPriceArticleEntity tmp = new ListPriceArticleEntity();
        tmp.setCodlistprice(codlistprice);
        tmp.setCodart(codart);
        ApiResponseObject<ListPriceArticleEntity> obj = this.listPriceArticleService.findById(tmp);
        return ResponseEntity.ok(obj);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ListPriceArticleEntity listPriceArticleEntity) throws GenericObjectServiceException {
        ApiResponseObject<ListPriceArticleEntity> obj = this.listPriceArticleService.save(listPriceArticleEntity);
        return ResponseEntity.ok(obj);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestParam(value = "codlistprice", defaultValue = "0") Integer codlistprice,
                                    @RequestParam(value = "codart", defaultValue = "") String codart,
                                    @RequestBody ListPriceArticleEntity listPriceArticleEntity) throws GenericObjectServiceException {
        listPriceArticleEntity.setCodlistprice(codlistprice);
        listPriceArticleEntity.setCodart(codart);
        ApiResponseObject<ListPriceArticleEntity> obj = this.listPriceArticleService.update(listPriceArticleEntity);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam(value = "codlistprice", defaultValue = "0") Integer codlistprice,
                                    @RequestParam(value = "codart", defaultValue = "") String codart,
                                    @RequestBody ListPriceArticleEntity listPriceArticleEntity) throws GenericObjectServiceException {
        listPriceArticleEntity.setCodlistprice(codlistprice);
        listPriceArticleEntity.setCodart(codart);
        ApiResponseObject<ListPriceArticleEntity> obj = this.listPriceArticleService.delete(listPriceArticleEntity);
        return ResponseEntity.ok(obj);
    }
}
