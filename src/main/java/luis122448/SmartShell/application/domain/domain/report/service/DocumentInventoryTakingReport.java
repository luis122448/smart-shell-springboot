package luis122448.SmartShell.application.domain.domain.report.service;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.model.ColumnInfo;
import luis122448.SmartShell.application.domain.domain.model.DocumentGenericPrintDTO;
import luis122448.SmartShell.application.domain.domain.report.constant.DIRECTORYConstants;
import luis122448.SmartShell.application.domain.domain.service.service.ArticleService;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentDetailService;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentHeaderService;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentDetailEntity;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import luis122448.SmartShell.application.domain.persistence.mapper.DocumentGenericMapper;
import luis122448.SmartShell.util.exception.GenericByteServiceException;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.ImportErrorModel;
import luis122448.SmartShell.util.object.api.ApiResponseByte;
import luis122448.SmartShell.util.object.api.ApiResponseReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static luis122448.SmartShell.application.domain.domain.report.constant.ARCHIVEConstants.*;
import static luis122448.SmartShell.application.domain.domain.report.constant.ARCHIVEConstants.EXPORT_FAILED;
import static luis122448.SmartShell.application.domain.domain.report.constant.DIRECTORYConstants.*;
import static luis122448.SmartShell.util.code.Utils.*;

@Slf4j
@Service
public class DocumentInventoryTakingReport {

    private final GenericReport genericReport;
    private final DocumentHeaderService documentHeaderService;
    private final DocumentDetailService documentDetailService;
    private final ArticleService articleService;
    private final DocumentGenericMapper documentGenericMapper;
    public static final String NAME_DOCUMENT_INVENTORY = "DOCUMENT INVENTORY TAKING IMPORT";
    public static final String TITLE_DOCUMENT_INVENTORY = "REPORT OF IMPORT ERROR OF DOCUMENT INVENTORY TAKING";

    public DocumentInventoryTakingReport(GenericReport genericReport, DocumentHeaderService documentHeaderService, DocumentDetailService documentDetailService, ArticleService articleService, DocumentGenericMapper documentGenericMapper) {
        this.genericReport = genericReport;
        this.documentHeaderService = documentHeaderService;
        this.documentDetailService = documentDetailService;
        this.articleService = articleService;
        this.documentGenericMapper = documentGenericMapper;
    }

    public ApiResponseReport<?> printDocument(Long numint) throws GenericListServiceException, JRException, FileNotFoundException {
        List<DocumentGenericPrintDTO> obj = this.documentGenericMapper.toListDocumentGenericPrintDTO(this.documentHeaderService.printDocumentGeneric(numint).getList().orElseThrow());
        JRDataSource dataSource = new JRBeanCollectionDataSource(obj);
        JasperPrint jasperPrint = JasperFillManager.fillReport(DIRECTORYConstants.getJasperInputStream(REPORT_INVOICE_A4_VERTICAL),new HashMap<>(), dataSource);
        return new ApiResponseReport<>(1,"Ok", Optional.of(jasperPrint));
    }

    public ApiResponseByte<?> importDetails(Long numint, MultipartFile multipartFile) throws GenericByteServiceException {
        try {
            this.genericReport.genericValidArchive(multipartFile, NAME_DOCUMENT_INVENTORY, EXCEL_EXTENSION);
            byte[] fileBytes = multipartFile.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
            ApiResponseReport<?> tmp = this.readXSSFWorkbook(numint, xssfWorkbook);
            JasperPrint jasperPrint = tmp.getJasperPrint().orElseThrow();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
            byte[] pdfBytes = outputStream.toByteArray();
            return new ApiResponseByte<>(tmp.getStatus(), tmp.getMessage(), Optional.of(pdfBytes), NAME_DOCUMENT_INVENTORY, PDF_FORMAT, PDF_EXTENSION);
        } catch (IOException | JRException e) {
            throw new GenericByteServiceException(EXPORT_FAILED, e.getMessage(), e);
        } catch (GenericObjectServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponseReport<?> readXSSFWorkbook(Long numint, XSSFWorkbook xssfWorkbook) throws IOException, JRException, GenericByteServiceException, GenericObjectServiceException {

        List<ColumnInfo> columnInfoList = readHeaderXSSFWorkbook(xssfWorkbook);
        DocumentHeaderEntity documentHeaderEntity = this.documentHeaderService.findByNumint(numint).getObject().orElseThrow(
                () -> new GenericObjectServiceException(404, "Document not found")
        );
        List<DocumentDetailEntity> documentDetailEntities = new ArrayList<>();
        XSSFSheet formatSheet = xssfWorkbook.getSheet(IMPORT_SHEET_PRINCIPAL);
        List<ImportErrorModel> importErrorModelListGeneral = new ArrayList<>();

        int startRow = IMPORT_START_ROW;
        int lastRow = formatSheet.getLastRowNum();
        long numite = 0;

        for (int i = startRow; i <= lastRow; i++) {

            DocumentDetailEntity documentDetailEntity = new DocumentDetailEntity();
            Row row = formatSheet.getRow(i);

            if(row == null){
                continue;
            }

            // Validate Required
            List<ImportErrorModel> importErrorModelList = validateFieldRequiredXSSFWorkbook(columnInfoList, row);
            importErrorModelListGeneral.addAll(importErrorModelList);
            // Register
            documentDetailEntity.setNumint(numint);
            documentDetailEntity.setNumite(numite);
            documentDetailEntity.setTypinv(getIntegerCellValue(row.getCell(0)));
            documentDetailEntity.setCodart(getStringCellValue(row.getCell(1)));
            documentDetailEntity.setEtiqueta(getLongCellValue(row.getCell(3)));
            documentDetailEntity.setQuantity(getBigDecimalCellValue(row.getCell(4)));
            documentDetailEntity.setPrice(getBigDecimalCellValue(row.getCell(5)));
            documentDetailEntity.setRegistdate(documentHeaderEntity.getRegistdate());
            documentDetailEntity.setCodbranch(documentHeaderEntity.getCodbranch());
            documentDetailEntity.setOriwarehouse(documentHeaderEntity.getOriwarehouse());
            documentDetailEntity.setDeswarehouse(documentHeaderEntity.getDeswarehouse());
            documentDetailEntities.add(documentDetailEntity);
            numite++;
        }
        if (!importErrorModelListGeneral.isEmpty()){
            Integer numberRow = lastRow - (startRow - 1);
            return this.genericReport.genericResponseReport(importErrorModelListGeneral,TITLE_DOCUMENT_INVENTORY, numberRow);
        }
        return validateAndImportList(documentDetailEntities, startRow, lastRow);
    }

    public ApiResponseReport<?> validateAndImportList(List<DocumentDetailEntity> documentDetailEntities, Integer startRow, Integer lastRow) throws JRException, GenericByteServiceException, FileNotFoundException, GenericObjectServiceException {
        List<ImportErrorModel> importErrorModelList = new ArrayList<>();
        Integer currentRow = startRow;
        for (DocumentDetailEntity entity : documentDetailEntities) {
            if(this.articleService.exist(entity.getTypinv(), entity.getCodart()).getStatus() < 0){
                importErrorModelList.add(new ImportErrorModel(currentRow, "typinv and codart", "The article does not exist"));
            }
            currentRow = currentRow + 1;
        }
        Integer numberRow = lastRow - (startRow - 1);
        if (importErrorModelList.isEmpty()){
            for (DocumentDetailEntity documentDetailEntity : documentDetailEntities) {
                this.documentDetailService.registerDocumentDetail(documentDetailEntity);
            }
        }
        return this.genericReport.genericResponseReport(importErrorModelList,TITLE_DOCUMENT_INVENTORY, numberRow);
    }

}
