package luis122448.SmartShell.application.archive.domain.service.implement;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.archive.domain.service.service.ArchiveService;
import luis122448.SmartShell.application.archive.persistence.entity.ArchiveEntity;
import luis122448.SmartShell.application.archive.persistence.repository.ArchiveRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class ArchiveServiceImpl implements ArchiveService {

    private final ArchiveRepository archiveRepository;

    public ArchiveServiceImpl(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Override
    public ApiResponseObject<ArchiveEntity> findArchive(String id) throws GenericObjectServiceException {
        Optional<ArchiveEntity> imageEntity = this.archiveRepository.findById(id);
        if (imageEntity.isEmpty()){
            throw new GenericObjectServiceException("ARCHIVE BY ID " + id + " NOT EXISTS!");
        }
        return new ApiResponseObject<ArchiveEntity>(1,"OK",imageEntity);
    }

    @Override
    public ApiResponseObject<ArchiveEntity> addArchive(ArchiveEntity archiveEntity, MultipartFile multipartFile) throws GenericObjectServiceException {
        try {
            log.info("Upload Archive");
            ArchiveEntity tmpImageEntity = new ArchiveEntity();
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename != null) {
                int lastDotIndex = originalFilename.lastIndexOf(".");
                if (lastDotIndex != -1) {
                    String name = originalFilename.substring(0, lastDotIndex);
                    String extension = originalFilename.substring(lastDotIndex);
                    String mimeType = multipartFile.getContentType();
                    tmpImageEntity.setTitle(name);
                    tmpImageEntity.setFormat(mimeType);
                    tmpImageEntity.setExtension(extension);
                }
            }
            tmpImageEntity.setSize((int) multipartFile.getSize());
            tmpImageEntity.setCompanyId(0L);
            tmpImageEntity.setStatus("Y");
            tmpImageEntity.setCreateBy("admin");
            tmpImageEntity.setCreateAt(LocalDateTime.now());
            tmpImageEntity.setArchive(
                    new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes())
            );
            log.info("Repository");
            ArchiveEntity objArchiveEntity = this.archiveRepository.insert(tmpImageEntity);
            return new ApiResponseObject<ArchiveEntity>(1,
                    "Ok",Optional.of(tmpImageEntity));
        } catch (IOException e){
            throw new GenericObjectServiceException("ERROR UNKNOWN", e);
        }

    }

    @Override
    public ApiResponseObject<ArchiveEntity> deleteArchive(String id) throws GenericObjectServiceException {
        try {
            log.info("Deleting Archive by ID: {}", id);
            Optional<ArchiveEntity> archiveEntityOptional = this.archiveRepository.findById(id);

            if (archiveEntityOptional.isEmpty()) {
                throw new GenericObjectServiceException("ARCHIVE BY ID " + id + " NOT FOUND!");
            }

            ArchiveEntity archiveEntity = archiveEntityOptional.get();
//            this.archiveRepository.deleteById(id);
            log.info("Archive with ID {} has been deleted.", id);

            return new ApiResponseObject<>(1, "OK", Optional.empty());
        } catch (Exception e) {
            log.error("Error {}, Cause: {}", e.getMessage(), e.getCause());
            throw new GenericObjectServiceException("Error deleting archive by ID: " + id);
        }
    }

}
