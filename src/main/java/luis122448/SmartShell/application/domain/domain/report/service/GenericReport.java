package luis122448.SmartShell.application.domain.domain.report.service;

import luis122448.SmartShell.application.domain.persistence.entity.CompanyInfoEntity;
import luis122448.SmartShell.application.domain.persistence.repository.constants.CompanyInfoRepository;
import luis122448.SmartShell.util.exception.GenericByteServiceException;
import luis122448.SmartShell.util.object.ImportErrorModel;
import luis122448.SmartShell.util.object.api.ApiResponseReport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static luis122448.SmartShell.application.domain.domain.report.constant.ARCHIVEConstants.*;
import static luis122448.SmartShell.application.domain.domain.report.constant.DIRECTORYConstants.*;

@Service
public class GenericReport {

    private final CompanyInfoRepository companyInfoRepository;

    public GenericReport(CompanyInfoRepository companyInfoRepository) {
        this.companyInfoRepository = companyInfoRepository;
    }

    public void genericValidArchive(MultipartFile multipartFile, String name, String extension) throws GenericByteServiceException{
        String originalFilename = multipartFile.getOriginalFilename();
        genericValidExtensionArchive(originalFilename, extension);
        genericValidNameArchive(originalFilename, name);
        if (multipartFile.getSize() > MAX_SIZE_BYTE){
            throw new GenericByteServiceException("THE FILED SIZE EXCEEDS THE MAXIMUM OF 100 MB");
        }
    }

    public void genericValidArchive(MultipartFile multipartFile, String name, String extension, Integer maxsize) throws GenericByteServiceException{
        String originalFilename = multipartFile.getOriginalFilename();
        genericValidExtensionArchive(originalFilename, extension);
        genericValidNameArchive(originalFilename, name);
        if (multipartFile.getSize() > maxsize * 1024 * 1024){
            throw  new GenericByteServiceException("THE FILED SIZE EXCEEDS THE MAXIMUM OF " + maxsize + " MB");
        }
    }

    public void genericValidExtensionArchive(String originalName, String extension) throws GenericByteServiceException {
        if (!extension.equalsIgnoreCase("all")) {
            List<String> allowedExtensions = List.of(extension.split(","));
            String fileExtension = getFileExtension(originalName);
            if (!allowedExtensions.contains(fileExtension.toLowerCase())) {
                throw new GenericByteServiceException("INCORRECT FILE, ONLY FILES WITH A [ " + extension + " ] EXTENSION ARE ACCEPTED");
            }
        }
    }

    public void genericValidNameArchive(String originalName, String name) throws GenericByteServiceException {
        if (!name.equalsIgnoreCase("all")) {
            if (!originalName.startsWith(name)) {
                throw new GenericByteServiceException("INCORRECT FILE, VERIFY THE NAME IS [ " + name + " ]");
            }
        }
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    public Map<String, Object> genericResponseInfo(String title, String status, Integer numberRow, Integer numberError) throws GenericByteServiceException{

        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        CompanyInfoEntity companyInfoEntity = this.companyInfoRepository.findById(1).orElse(new CompanyInfoEntity());
        if (companyInfoEntity.getGloss() == null){
            throw new GenericByteServiceException("THE COMPANY GLOSS IS NULL");
        }
        InputStream comglossInputStream = new ByteArrayInputStream(companyInfoEntity.getGloss());
        params.put("comgloss", comglossInputStream);
        params.put("company", companyInfoEntity.getComnam());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            params.put("username", authentication.getName());
        } else {
            params.put("username", "Unknown");
        }
        params.put("author", "Luis Antonio Calvo Quispe");
        params.put("numberRow", numberRow);
        params.put("numberError", numberError );
        params.put("status",status);
        return params;
    }

    public ApiResponseReport<?> genericResponseReport(List<ImportErrorModel> importErrorModelList,String title, Integer numberRow) throws JRException, GenericByteServiceException {
        if (importErrorModelList.isEmpty()){
            Map<String, Object> params = this.genericResponseInfo(title,IMPORT_SUCCESS,numberRow,0);
            JasperPrint jasperPrint = JasperFillManager.fillReport(REPORT_SUCCESS_IMPORT_A4_HORIZONTAL,params, new JREmptyDataSource());
            return new ApiResponseReport<>(1,IMPORT_SUCCESS, Optional.of(jasperPrint));
        } else {
            JRDataSource dataSource = new JRBeanCollectionDataSource(importErrorModelList);
            Integer numberError = importErrorModelList.size();
            Map<String, Object> params = this.genericResponseInfo(title,IMPORT_FAILED,numberRow,numberError);
            JasperPrint jasperPrint = JasperFillManager.fillReport(REPORT_ERROR_IMPORT_A4_HORIZONTAL,params, dataSource);
            return new ApiResponseReport<>(-1,IMPORT_FAILED, Optional.of(jasperPrint));
        }
    }

}
