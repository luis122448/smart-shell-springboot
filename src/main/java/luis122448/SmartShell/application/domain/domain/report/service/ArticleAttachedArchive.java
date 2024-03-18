package luis122448.SmartShell.application.domain.domain.report.service;

import luis122448.SmartShell.application.archive.domain.service.implement.ArchiveServiceImpl;
import luis122448.SmartShell.application.archive.persistence.entity.ArchiveEntity;
import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleAttachedEntity;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleSpecificationEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleAttachedPK;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticlePK;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleSpecificationPK;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleAttachedRepository;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleRepository;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleSpecificationRepository;
import luis122448.SmartShell.util.exception.GenericByteServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseByte;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.mock.web.MockMultipartFile;
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
    private final SecurityContextInitializer securityContextInitializer;
    public ArticleAttachedArchive(ArticleRepository articleRepository, ArticleAttachedRepository articleAttachedRepository, ArticleSpecificationRepository articleSpecificationRepository, ArchiveServiceImpl archiveService, GenericReport genericReport, SecurityContextInitializer securityContextInitializer) {
        this.articleRepository = articleRepository;
        this.articleAttachedRepository = articleAttachedRepository;
        this.articleSpecificationRepository = articleSpecificationRepository;
        this.archiveService = archiveService;
        this.genericReport = genericReport;
        this.securityContextInitializer = securityContextInitializer;
    }

    public ArticleSpecificationEntity validateSave(ArticleAttachedEntity articleAttachedEntity) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        if (articleAttachedEntity.getTypspe() <= 0){
            throw new GenericObjectServiceException("CODE OF SPECIFICATION IS REQUIRED!");
        }
        ArticleEntity articleEntity = this.articleRepository.findById(new ArticlePK(idcompany, articleAttachedEntity.getCodart())).orElseThrow(
                () -> new GenericObjectServiceException("ARTICLE NOT EXISTS!")
        );
        return this.articleSpecificationRepository.findById(new ArticleSpecificationPK(idcompany, articleEntity.getTypinv(), articleAttachedEntity.getTypspe())).orElseThrow(
                () -> new GenericObjectServiceException("SPECIFICATION NOT EXISTS!")
        );
    }

    public ApiResponseByte<?> downloaderArchive(String codart, Integer typeps) throws GenericObjectServiceException{
        Integer idcompany = securityContextInitializer.getIdCompany();
        ArticleAttachedEntity articleAttachedEntity =  this.articleAttachedRepository.findById(new ArticleAttachedPK(idcompany,codart, typeps)).orElseThrow();
        if (articleAttachedEntity.getIdMongo().isEmpty()){
            throw new GenericObjectServiceException("THE REQUESTED FILE DOES NOT EXIST");
        }
        ArchiveEntity archiveEntity = this.archiveService.findArchive(articleAttachedEntity.getIdMongo()).getObject().orElseThrow();
        byte[] bytes = archiveEntity.getArchive().getData();
        return new ApiResponseByte<>(1,"DOWNLOADER SUCCESS",Optional.of(bytes), archiveEntity.getTitle() , archiveEntity.getFormat(), archiveEntity.getExtension() );
    }

    public ApiResponseObject<ArticleAttachedEntity> save(ArticleAttachedEntity articleAttachedEntity,  List<ArchiveEntity> archiveEntityList, List<MultipartFile> multipartFileList) throws GenericObjectServiceException, GenericByteServiceException {
        String coduser = securityContextInitializer.getCodUser();
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
        articleAttachedEntity.setCreateby(coduser);
        articleAttachedEntity.setUpdateby(coduser);
        return new ApiResponseObject<ArticleAttachedEntity>(Optional.of(this.articleAttachedRepository.save(articleAttachedEntity)));
    }

    public ApiResponseObject<ArticleAttachedEntity> delete(String codart, Integer typspe) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        ArticleAttachedEntity articleAttachedEntity = this.articleAttachedRepository.findById(new ArticleAttachedPK(idcompany, codart, typspe)).orElseThrow();
        this.archiveService.deleteArchive(articleAttachedEntity.getIdMongo());
        this.articleAttachedRepository.deleteById(new ArticleAttachedPK(idcompany, codart, typspe));
        return new ApiResponseObject<ArticleAttachedEntity>(Optional.empty());
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
