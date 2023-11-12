package luis122448.SmartShell.application.domain.domain.report.service;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.model.ColumnInfo;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleRepository;
import luis122448.SmartShell.application.domain.persistence.repository.TypeInventoryRepository;
import luis122448.SmartShell.util.exception.GenericByteServiceException;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.ImportErrorModel;
import luis122448.SmartShell.util.object.api.ApiResponseByte;
import luis122448.SmartShell.util.object.api.ApiResponseReport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import static luis122448.SmartShell.application.domain.domain.report.constant.ARCHIVEConstants.*;
import static luis122448.SmartShell.application.domain.domain.report.constant.DIRECTORYConstants.*;
import static luis122448.SmartShell.util.code.Utils.*;

@Slf4j
@Service
public class ArticleReport {

    private final ArticleRepository articleRepository;
    private final TypeInventoryRepository typeInventoryRepository;
    private final GenericReport genericReport;

    public ArticleReport(ArticleRepository articleRepository, TypeInventoryRepository typeInventoryRepository, GenericReport genericReport) {
        this.articleRepository = articleRepository;
        this.typeInventoryRepository = typeInventoryRepository;
        this.genericReport = genericReport;
    }
    public static final String NAME_ARTICLE = "ARTICLE IMPORT";
    public static final String TITLE_ARTICLE = "REPORT OF IMPORT ERROR OF ARTICLE";
    public ApiResponseByte<?> exportByExcel(Integer typinv) throws GenericByteServiceException, GenericListServiceException {
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            XSSFWorkbook xssfWorkbook = this.generateXSSFWorkbook(typinv);
            xssfWorkbook.write(byteArrayOutputStream);
            byte[] excelBytes = byteArrayOutputStream.toByteArray();
            return new ApiResponseByte<>(1,EXPORT_SUCCESS, Optional.of(excelBytes),NAME_ARTICLE,EXCEL_FORMAT,EXCEL_EXTENSION);
        } catch ( FileNotFoundException e ) {
            throw new GenericByteServiceException("REPORTED NOT IMPLEMENT",e.getMessage(),e);
        } catch ( IOException e ) {
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    public ApiResponseByte<?> importByExcel(Integer typinv, MultipartFile multipartFile) throws GenericByteServiceException {
        try {
            this.genericReport.genericValidArchive(multipartFile, NAME_ARTICLE, EXCEL_EXTENSION);
            byte[] fileBytes = multipartFile.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
            ApiResponseReport<?> tmp = this.readXSSFWorkbook(typinv, xssfWorkbook);
            JasperPrint jasperPrint = tmp.getJasperPrint().orElseThrow();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
            byte[] pdfBytes = outputStream.toByteArray();
            return new ApiResponseByte<>(tmp.getStatus(),tmp.getMessage(), Optional.of(pdfBytes),NAME_ARTICLE,PDF_FORMAT,PDF_EXTENSION);
        } catch (IOException | JRException e){
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    private XSSFWorkbook generateXSSFWorkbook(Integer typinv) throws GenericByteServiceException, GenericListServiceException {
        try{
            List<ArticleEntity> articleEntityList = this.articleRepository.findByTypinv(typinv);
            File file = new File(FORMAT_ARTICLE);
            InputStream inputStream = new FileInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet format = xssfWorkbook.getSheet(IMPORT_SHEET_PRINCIPAL);

            int startRow = IMPORT_START_ROW;

            for (int i = startRow; i < articleEntityList.size() + startRow; i++) {
                Row rowData = format.createRow(i);

                Cell cellData = rowData.createCell(0);
                cellData.setCellValue(articleEntityList.get(i - startRow).getCodart());

                cellData = rowData.createCell(1);
                cellData.setCellValue(articleEntityList.get(i - startRow).getAbrevi());

                cellData = rowData.createCell(2);
                cellData.setCellValue(articleEntityList.get(i - startRow).getDescri());

                cellData = rowData.createCell(3);
                cellData.setCellValue(articleEntityList.get(i - startRow).getCodext());

                cellData = rowData.createCell(4);
                cellData.setCellValue(articleEntityList.get(i - startRow).getCodbar());

                cellData = rowData.createCell(5);
                cellData.setCellValue(articleEntityList.get(i - startRow).getCodean());

                cellData = rowData.createCell(6);
                cellData.setCellValue(articleEntityList.get(i - startRow).getCstock());

                cellData = rowData.createCell(7);
                cellData.setCellValue(articleEntityList.get(i - startRow).getCodprv());

                cellData = rowData.createCell(8);
                cellData.setCellValue(articleEntityList.get(i - startRow).getCodman());

                cellData = rowData.createCell(9);
                cellData.setCellValue(articleEntityList.get(i - startRow).getCoduni());
            }
            return xssfWorkbook;
        }
        catch ( FileNotFoundException e ) {
            throw new GenericByteServiceException("REPORTED NOT IMPLEMENT",e.getMessage(),e);
        } catch ( IOException e ) {
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    public ApiResponseReport<?> readXSSFWorkbook(Integer typinv, XSSFWorkbook xssfWorkbook) throws IOException, JRException {

        List<ColumnInfo> columnInfoList = readHeaderXSSFWorkbook(xssfWorkbook);
        for (ColumnInfo columnInfo : columnInfoList) {
            System.out.println("Nombre de la columna: " + columnInfo.getName());
            System.out.println("getRequired: " + columnInfo.getRequired());
            // Agrega aquí cualquier otra propiedad que desees mostrar
            System.out.println("------------------------------------------");
        }
        List<ArticleEntity> articleEntityList = new ArrayList<>();
        XSSFSheet formatSheet = xssfWorkbook.getSheet(IMPORT_SHEET_PRINCIPAL);
        List<ImportErrorModel> importErrorModelListGeneral = new ArrayList<>();

        int startRow = IMPORT_START_ROW;
        int lastRow = formatSheet.getLastRowNum();
        log.info("lastRow {}",lastRow);
        for (int i = startRow; i <= lastRow; i++) {
            log.info("i {}",i);
            ArticleEntity articleEntity = new ArticleEntity();

            Row row = formatSheet.getRow(i);
            if(row == null){
                log.info("Index Null {}", i);
                continue;
            }
            // Validate Required
            List<ImportErrorModel> importErrorModelList = validateFieldRequiredXSSFWorkbook(columnInfoList, row);
            importErrorModelListGeneral.addAll(importErrorModelList);

            // Register
            articleEntity.setCodart(getStringCellValue(row.getCell(0)));
            articleEntity.setTypinv(typinv);
            articleEntity.setAbrevi(getStringCellValue(row.getCell(1)));
            articleEntity.setDescri(getStringCellValue(row.getCell(2)));
            articleEntity.setCodext(getStringCellValue(row.getCell(3)));
            articleEntity.setCodbar(getStringCellValue(row.getCell(4)));
            articleEntity.setCodean(getStringCellValue(row.getCell(5)));
            articleEntity.setCstock(getStringCellValue(row.getCell(6)));
            articleEntity.setCodprv(getStringCellValue(row.getCell(7)));
            articleEntity.setCodman(getStringCellValue(row.getCell(8)));
            articleEntity.setCoduni(getStringCellValue(row.getCell(9)));
            articleEntity.setStatus("Y");
            articleEntity.setUpdateby("admin");
            articleEntity.setUpdateat(LocalDateTime.now());
            articleEntityList.add(articleEntity);
        }
        if (importErrorModelListGeneral.size() != 0){
            Integer numberRow = lastRow - (startRow - 1);
            return this.genericReport.genericResponseReport(importErrorModelListGeneral,TITLE_ARTICLE, numberRow);
        }
        return validateAndImportList(articleEntityList, startRow, lastRow);
    }

    public ApiResponseReport<?> validateAndImportList(List<ArticleEntity> articleEntityList, Integer startRow, Integer lastRow) throws JRException {

        List<ImportErrorModel> importErrorModelList = new ArrayList<>();

        Integer currentRow = startRow;
        for (ArticleEntity entity : articleEntityList) {
            if(!this.typeInventoryRepository.existsById(entity.getTypinv())){
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow, entity.getTypinv().toString(),"TYPE INVENTORY NOT EXISTS");
                importErrorModelList.add(importErrorModel);
            }
            if(!entity.getCstock().equals("UNITY")){
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow, entity.getTypinv().toString(),"TYPE CONTROL OF STOCK NOT EXISTS");
                importErrorModelList.add(importErrorModel);
            }
            if(!entity.getCstock().equals("UNITY")){
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow, entity.getTypinv().toString(),"UNIT NOT EXISTS");
                importErrorModelList.add(importErrorModel);
            }
            currentRow = currentRow + 1;
        }
        Integer numberRow = lastRow - (startRow - 1);
        if (importErrorModelList.size() == 0){
            this.articleRepository.saveAll(articleEntityList);
        }
        return this.genericReport.genericResponseReport(importErrorModelList,TITLE_ARTICLE, numberRow);
    }
}
