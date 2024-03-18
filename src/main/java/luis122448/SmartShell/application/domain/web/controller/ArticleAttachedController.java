package luis122448.SmartShell.application.domain.web.controller;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.archive.persistence.entity.ArchiveEntity;
import luis122448.SmartShell.application.domain.domain.report.service.ArticleAttachedArchive;
import luis122448.SmartShell.application.domain.domain.service.service.ArticleAttachedService;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleAttachedEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleAttachedPK;
import luis122448.SmartShell.util.exception.GenericByteServiceException;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseByte;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.PATH_BILLING;

@Slf4j
@RestController
@RequestMapping(PATH_BILLING + "/article-attached")
public class ArticleAttachedController {

    private final ArticleAttachedArchive articleAttachedArchive;
    private final ArticleAttachedService articleAttachedService;
    public ArticleAttachedController(ArticleAttachedArchive articleAttachedArchive, ArticleAttachedService articleAttachedService) {
        this.articleAttachedArchive = articleAttachedArchive;
        this.articleAttachedService = articleAttachedService;
    }

    @GetMapping("/by-all")
    public ResponseEntity<?> findByAll(@RequestParam("codart") String codart) throws GenericListServiceException{
        ArticleAttachedEntity articleAttachedEntity = new ArticleAttachedEntity();
        articleAttachedEntity.setCodart(codart);
        ApiResponseList<ArticleAttachedEntity> apiResponseList = this.articleAttachedService.findByLike(articleAttachedEntity);
        return ResponseEntity.ok(apiResponseList);
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> findById(@RequestParam("codart") String codart,
                                      @RequestParam("typeps") Integer typeps) throws GenericObjectServiceException{
        ArticleAttachedPK articleAttachedPK = new ArticleAttachedPK(0,codart,typeps);
        return ResponseEntity.ok(this.articleAttachedService.findById(articleAttachedPK));
    }

    @GetMapping("/by-downloader")
    public ResponseEntity<?> findByDownloader(@RequestParam("codart") String codart,
                                       @RequestParam("typeps") Integer typeps) throws GenericObjectServiceException{
        ApiResponseByte<?> apiResponseByte = this.articleAttachedArchive.downloaderArchive(codart,typeps);
        return ResponseEntity.ok(apiResponseByte);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestParam("codart") String codart,
                                  @RequestParam("typspe") Integer typspe,
                                @RequestParam("observ") String observ,
                                @RequestParam("archiveEntityList") List<ArchiveEntity> archiveEntityList,
                                @RequestParam("multipartFileList") List<MultipartFile> multipartFileList
    ) throws GenericByteServiceException, GenericObjectServiceException {
        ArticleAttachedEntity articleAttachedEntity = new ArticleAttachedEntity(0,codart,typspe,"",observ,"");
        ApiResponseObject<ArticleAttachedEntity> apiResponseObject = this.articleAttachedArchive.save(articleAttachedEntity, archiveEntityList, multipartFileList);
        return ResponseEntity.ok(apiResponseObject);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam("codart") String codart,
                                    @RequestParam("typspe") Integer typspe) throws GenericObjectServiceException{
        ApiResponseObject<ArticleAttachedEntity> apiResponseObject = this.articleAttachedArchive.delete(codart, typspe);
        return ResponseEntity.ok(apiResponseObject);
    }

}
