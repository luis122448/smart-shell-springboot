package luis122448.SmartShell.application.domain.web.controller;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.model.ListPriceArticleDTO;
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
        ListPriceArticleDTO dto = new ListPriceArticleDTO();
        dto.setCodlistprice(codlistprice);
        dto.setCodart(codart);
        dto.setDesart(desart);
        return ResponseEntity.ok(this.listPriceArticleService.findByPage(dto, pageable));
    }

    @GetMapping("/by-codlistprice")
    public ResponseEntity<?> findByCodlistprice(@RequestParam(value = "codlistprice") Integer codlistprice,
                                     @RequestParam(value = "status", defaultValue = "") String status) throws GenericListServiceException {
        return ResponseEntity.ok(this.listPriceArticleService.findByCodlistprice(codlistprice, status));
    }

    @GetMapping("/by-codart")
    public ResponseEntity<?> findByCodart(@RequestParam(value = "codart") String codart,
                                          @RequestParam(value = "status", defaultValue = "") String status) throws GenericListServiceException {
        return ResponseEntity.ok(this.listPriceArticleService.findByCodart(codart,status));
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam(value = "codlistprice", defaultValue = "0") Integer codlistprice,
                                     @RequestParam(value = "codart", defaultValue = "") String codart) throws GenericObjectServiceException {
        ListPriceArticleDTO dto = new ListPriceArticleDTO();
        dto.setCodlistprice(codlistprice);
        dto.setCodart(codart);
        return ResponseEntity.ok(this.listPriceArticleService.findById(dto));
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ListPriceArticleDTO dto) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.listPriceArticleService.save(dto));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody ListPriceArticleDTO dto) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.listPriceArticleService.update(dto));
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam(value = "codlistprice", defaultValue = "0") Integer codlistprice,
                                    @RequestParam(value = "codart", defaultValue = "") String codart) throws GenericObjectServiceException {
        ListPriceArticleDTO dto = new ListPriceArticleDTO();
        dto.setCodlistprice(codlistprice);
        dto.setCodart(codart);
        return ResponseEntity.ok(this.listPriceArticleService.delete(dto));
    }
}
