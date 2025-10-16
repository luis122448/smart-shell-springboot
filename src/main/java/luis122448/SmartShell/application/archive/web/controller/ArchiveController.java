package luis122448.SmartShell.application.archive.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.archive.domain.service.service.ArchiveService;
import luis122448.SmartShell.application.archive.persistence.entity.ArchiveEntity;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static luis122448.SmartShell.application.archive.web.constant.APIArchiveConstants.*;

@Slf4j
@RestController
@RequestMapping(PATH_ARCHIVE)
public class ArchiveController {

    private final ArchiveService archiveService;

    public ArchiveController(ArchiveService imageService) {
        this.archiveService = imageService;
    }

    @Operation(tags = {TAG_ARCHIVE})
    @GetMapping("{id}")
    public ResponseEntity<?> findImage(@PathVariable String id) throws GenericObjectServiceException {
        ApiResponseObject<ArchiveEntity> object = this.archiveService.findArchive(id);
        return ResponseEntity.ok(object);
    }

    @Operation(tags = {TAG_ARCHIVE})
    @PostMapping("/save")
    public ResponseEntity<?> addImage(@RequestParam("archive") MultipartFile multipartFile) throws GenericObjectServiceException {
        ArchiveEntity archiveEntity = new ArchiveEntity();
//        archiveEntity.setTitle(title);
//        archiveEntity.setFormat(format);
//        archiveEntity.setExtension(extension);
        ApiResponseObject<ArchiveEntity> object = this.archiveService.addArchive(archiveEntity, multipartFile);
        return ResponseEntity.ok(object);
    }

}
