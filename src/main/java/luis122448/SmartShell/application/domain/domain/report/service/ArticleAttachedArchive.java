package luis122448.SmartShell.application.domain.domain.report.service;

import luis122448.SmartShell.application.archive.domain.service.implement.ArchiveServiceImpl;
import luis122448.SmartShell.application.archive.persistence.entity.ArchiveEntity;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleAttachedEntity;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleSpecificationEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleAttachedPK;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleSpecificationPK;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleAttachedRepository;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleRepository;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleSpecificationRepository;
import luis122448.SmartShell.util.exception.GenericByteServiceException;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseByte;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ArticleAttachedArchive {

    private final ArticleRepository articleRepository;
    private final ArticleAttachedRepository articleAttachedRepository;
    private final ArticleSpecificationRepository articleSpecificationRepository;
    private final ArchiveServiceImpl archiveService;
    private final GenericReport genericReport;
    public ArticleAttachedArchive(ArticleRepository articleRepository, ArticleAttachedRepository articleAttachedRepository, ArticleSpecificationRepository articleSpecificationRepository, ArchiveServiceImpl archiveService, GenericReport genericReport) {
        this.articleRepository = articleRepository;
        this.articleAttachedRepository = articleAttachedRepository;
        this.articleSpecificationRepository = articleSpecificationRepository;
        this.archiveService = archiveService;
        this.genericReport = genericReport;
    }

    public ArticleSpecificationEntity validateSave(ArticleAttachedEntity articleAttachedEntity) throws GenericObjectServiceException {
        if (articleAttachedEntity.getTypspe() <= 0){
            throw new GenericObjectServiceException("CODE OF SPECIFICATION IS REQUIRED!");
        }
        Optional<ArticleEntity> articleEntityOptional = this.articleRepository.findById(articleAttachedEntity.getCodart());
        if (articleEntityOptional.isEmpty()){
            throw new GenericObjectServiceException("ARTICLE NOT EXISTS!");
        }
        Optional<ArticleSpecificationEntity> articleSpecificationEntityOptional = this.articleSpecificationRepository.findById(new ArticleSpecificationPK(articleEntityOptional.get().getTypinv(),articleAttachedEntity.getTypspe()));
        if (articleSpecificationEntityOptional.isEmpty()){
            throw new GenericObjectServiceException("SPECIFICATION NOT EXISTS!");
        }
        return articleSpecificationEntityOptional.get();
    }

    public ApiResponseByte<?> downloaderArchive(String codart, Integer typeps) throws GenericObjectServiceException{
        ArticleAttachedEntity articleAttachedEntity =  this.articleAttachedRepository.findById(new ArticleAttachedPK(codart, typeps)).orElseThrow();
        if (articleAttachedEntity.getIdMongo().isEmpty()){
            throw new GenericObjectServiceException("THE REQUESTED FILE DOES NOT EXIST");
        }
        ArchiveEntity archiveEntity = this.archiveService.findArchive(articleAttachedEntity.getIdMongo()).getObject().orElseThrow();
        byte[] bytes = archiveEntity.getArchive().getData();
        return new ApiResponseByte<>(1,"DOWNLOADER SUCESS",Optional.of(bytes), archiveEntity.getTitle() , archiveEntity.getFormat(), archiveEntity.getExtension() );
    }

    public ApiResponseObject<ArticleAttachedEntity> save(ArticleAttachedEntity articleAttachedEntity,  List<ArchiveEntity> archiveEntityList, List<MultipartFile> multipartFileList) throws GenericObjectServiceException, GenericByteServiceException {

        ArticleSpecificationEntity articleSpecificationEntity = validateSave(articleAttachedEntity);
        if (articleSpecificationEntity.getMultip().equals("N")){
            if (multipartFileList.size() > 1) {
                throw new GenericObjectServiceException("THE SPECIFICATION ONLY ADMITS A SINGLE ATTACHMENT");
            }
            if (multipartFileList.isEmpty()) {
                throw new GenericObjectServiceException("NO FILE UPLOAD");
            }
            this.genericReport.genericValidArchive(multipartFileList.get(0), articleSpecificationEntity.getTitle(), articleSpecificationEntity.getExtension(), articleSpecificationEntity.getMaxsize());
            ApiResponseObject<ArchiveEntity> apiResponseObject =  this.archiveService.addArchive(new ArchiveEntity(), multipartFileList.get(0));
            articleAttachedEntity.setArchive(multipartFileList.get(0).getOriginalFilename());
            articleAttachedEntity.setIdMongo(apiResponseObject.getObject().orElseThrow().getId());
        } else {
            List<MultipartFile> validFiles = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFileList) {
                this.genericReport.genericValidArchive(multipartFile, articleSpecificationEntity.getTitle(), articleSpecificationEntity.getExtension(), articleSpecificationEntity.getMaxsize());
                validFiles.add(multipartFile);
            }
            MultipartFile zipFile = createZipFile(articleSpecificationEntity.getTitle(),validFiles);
            ArchiveEntity archiveEntity = new ArchiveEntity();
            archiveEntity.setFormat("application/zip");
            archiveEntity.setExtension(".zip");
            archiveEntity.setTitle(articleSpecificationEntity.getTitle());
            ApiResponseObject<ArchiveEntity> apiResponseObject =  this.archiveService.addArchive(archiveEntity, zipFile);
            articleAttachedEntity.setArchive(articleSpecificationEntity.getTitle()+".zip");
            articleAttachedEntity.setIdMongo(apiResponseObject.getObject().orElseThrow().getId());
        }
        articleAttachedEntity.setCreateat(LocalDateTime.now());
        articleAttachedEntity.setUpdateat(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            articleAttachedEntity.setCreateby(authentication.getName());
            articleAttachedEntity.setUpdateby(authentication.getName());
        } else {
            articleAttachedEntity.setCreateby("Unknown");
            articleAttachedEntity.setUpdateby("Unknown");
        }
        return new ApiResponseObject<ArticleAttachedEntity>(Optional.of(this.articleAttachedRepository.save(articleAttachedEntity)));
    }

    public ApiResponseObject<ArticleAttachedEntity> delete(String codart, Integer typspe) throws GenericObjectServiceException {
        ArticleAttachedEntity articleAttachedEntity = this.articleAttachedRepository.findById(new ArticleAttachedPK(codart, typspe)).orElseThrow();
        this.archiveService.deleteArchive(articleAttachedEntity.getIdMongo());
        this.articleAttachedRepository.deleteById(new ArticleAttachedPK(codart, typspe));
        return new ApiResponseObject<ArticleAttachedEntity>(1, "DELETE SUCCESS",Optional.empty());
    }

    private MultipartFile createZipFile(String title,  List<MultipartFile> validFiles) throws GenericObjectServiceException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            for (MultipartFile multipartFile : validFiles) {
                ZipEntry zipEntry = new ZipEntry(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(multipartFile.getBytes());
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            throw new GenericObjectServiceException("ERROR UNKNOWN",e);
        }
        return new MockMultipartFile(title + ".zip", "attachment.zip", "application/zip", outputStream.toByteArray());
    }
}
