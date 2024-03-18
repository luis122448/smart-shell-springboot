package luis122448.SmartShell.application.domain.domain.report.service;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.ColumnInfo;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceArticleEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticlePK;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleRepository;
import luis122448.SmartShell.application.domain.persistence.repository.ListPriceArticleRepository;
import luis122448.SmartShell.util.exception.GenericByteServiceException;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.ImportErrorModel;
import luis122448.SmartShell.util.object.api.ApiResponseByte;
import luis122448.SmartShell.util.object.api.ApiResponseReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static luis122448.SmartShell.application.domain.domain.report.constant.ARCHIVEConstants.*;
import static luis122448.SmartShell.application.domain.domain.report.constant.DIRECTORYConstants.*;
import static luis122448.SmartShell.util.code.Utils.*;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class ListPriceArticleReport {
    private final ListPriceArticleRepository listPriceArticleRepository;
    private final ArticleRepository articleRepository;
    private final GenericReport genericReport;
    private final SecurityContextInitializer securityContextInitializer;
    public ListPriceArticleReport(ListPriceArticleRepository listPriceArticleRepository, ArticleRepository articleRepository, GenericReport genericReport, SecurityContextInitializer securityContextInitializer) {
        this.listPriceArticleRepository = listPriceArticleRepository;
        this.articleRepository = articleRepository;
        this.genericReport = genericReport;
        this.securityContextInitializer = securityContextInitializer;
    }
    public static final String NAME_PRICE_LIST_ARTICLE = "LIST PRICE ARTICLE IMPORT";
    public static final String TITLE_PRICE_LIST_ARTICLE = "REPORT OF IMPORT ERROR OF LIST PRICE BY ARTICLE";
    public ApiResponseByte<?> exportByExcel(Integer codlistprice) throws GenericByteServiceException, GenericListServiceException {
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (XSSFWorkbook xssfWorkbook = this.exportXSSFWorkbook(codlistprice)) {
                xssfWorkbook.write(byteArrayOutputStream);
            }
            byte[] excelBytes = byteArrayOutputStream.toByteArray();
            return new ApiResponseByte<>(1,EXPORT_SUCCESS, Optional.of(excelBytes),NAME_PRICE_LIST_ARTICLE,EXCEL_FORMAT,EXCEL_EXTENSION);
        } catch ( IOException e ) {
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    public ApiResponseByte<?> generateByExcel(Integer codlistprice) throws GenericByteServiceException, GenericListServiceException {
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (XSSFWorkbook xssfWorkbook = this.generateXSSFWorkbook(codlistprice)) {
                xssfWorkbook.write(byteArrayOutputStream);
            }
            byte[] excelBytes = byteArrayOutputStream.toByteArray();
            return new ApiResponseByte<>(1,EXPORT_SUCCESS, Optional.of(excelBytes),NAME_PRICE_LIST_ARTICLE,EXCEL_FORMAT,EXCEL_EXTENSION);
        } catch ( IOException e ) {
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    public ApiResponseByte<?> importByExcel(Integer codlistprice, MultipartFile multipartFile) throws GenericByteServiceException {
        try {
            this.genericReport.genericValidArchive(multipartFile, NAME_PRICE_LIST_ARTICLE, EXCEL_EXTENSION);
            byte[] fileBytes = multipartFile.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
            ApiResponseReport<?> tmp = this.importXSSFWorkbook(codlistprice, xssfWorkbook);
            JasperPrint jasperPrint = tmp.getJasperPrint().orElseThrow();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
            byte[] pdfBytes = outputStream.toByteArray();
            return new ApiResponseByte<>(tmp.getStatus(),tmp.getMessage(), Optional.of(pdfBytes),NAME_PRICE_LIST_ARTICLE,PDF_FORMAT,PDF_EXTENSION);
        } catch (IOException | JRException e){
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    private XSSFWorkbook exportXSSFWorkbook(Integer codlistprice) throws GenericByteServiceException, GenericListServiceException {
        try{
            Integer idcompany = securityContextInitializer.getIdCompany();
            List<ListPriceArticleEntity> listPriceArticleEntityList = this.listPriceArticleRepository.findByIdcompanyAndCodlistprice(idcompany,codlistprice);
            File file = new File(FORMAT_PRICE_LIST_ARTICLE);
            InputStream inputStream = new FileInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet format = xssfWorkbook.getSheet(IMPORT_SHEET_PRINCIPAL);

            int startRow = IMPORT_START_ROW;

            CellStyle priceCellStyle = importStyle(xssfWorkbook);
            CellStyle descCellStyle = discountStyle(xssfWorkbook);

            for (int i = startRow; i < listPriceArticleEntityList.size() + startRow; i++) {
                Row rowData = format.createRow(i);

                Cell cellData = rowData.createCell(0);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getCodart());

                cellData = rowData.createCell(1);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesart());

                cellData = rowData.createCell(2);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getPrice().doubleValue());
                cellData.setCellStyle(priceCellStyle);

                cellData = rowData.createCell(3);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getModprice());

                cellData = rowData.createCell(4);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getModdesc());

                cellData = rowData.createCell(5);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesmax().doubleValue());

                cellData = rowData.createCell(6);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesc01().doubleValue());
                cellData.setCellStyle(descCellStyle);

                cellData = rowData.createCell(7);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesc02().doubleValue());
                cellData.setCellStyle(descCellStyle);

                cellData = rowData.createCell(8);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesc03().doubleValue());
                cellData.setCellStyle(descCellStyle);

                cellData = rowData.createCell(9);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesc04().doubleValue());
                cellData.setCellStyle(descCellStyle);
            }
            return xssfWorkbook;
        }
        catch ( FileNotFoundException e ) {
            throw new GenericByteServiceException("REPORTED NOT IMPLEMENT",e.getMessage(),e);
        } catch ( IOException e ) {
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    private XSSFWorkbook generateXSSFWorkbook(Integer codlistprice) throws GenericByteServiceException, GenericListServiceException {
        try{
            Integer idcompany = securityContextInitializer.getIdCompany();
            List<ListPriceArticleEntity> listPriceArticleEntityList = this.listPriceArticleRepository.findByIdcompanyAndCodlistprice(idcompany,codlistprice);
            List<ArticleEntity> listPriceNotArticleEntityList = this.articleRepository.findByArticleNotExistsListPrice(idcompany,codlistprice);
            File file = new File(FORMAT_PRICE_LIST_ARTICLE);
            InputStream inputStream = new FileInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet format = xssfWorkbook.getSheet(IMPORT_SHEET_PRINCIPAL);

            int startRow = IMPORT_START_ROW;

            CellStyle priceCellStyle = importStyle(xssfWorkbook);
            CellStyle descCellStyle = discountStyle(xssfWorkbook);

            for (int i = startRow; i < listPriceArticleEntityList.size() + startRow; i++) {
                Row rowData = format.createRow(i);

                Cell cellData = rowData.createCell(0);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getCodart());

                cellData = rowData.createCell(1);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesart());

                cellData = rowData.createCell(2);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getPrice().doubleValue());
                cellData.setCellStyle(priceCellStyle);

                cellData = rowData.createCell(3);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getModprice());

                cellData = rowData.createCell(4);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getModdesc());

                cellData = rowData.createCell(5);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesmax().doubleValue());

                cellData = rowData.createCell(6);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesc01().doubleValue());
                cellData.setCellStyle(descCellStyle);

                cellData = rowData.createCell(7);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesc02().doubleValue());
                cellData.setCellStyle(descCellStyle);

                cellData = rowData.createCell(8);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesc03().doubleValue());
                cellData.setCellStyle(descCellStyle);

                cellData = rowData.createCell(9);
                cellData.setCellValue(listPriceArticleEntityList.get(i - startRow).getDesc04().doubleValue());
                cellData.setCellStyle(descCellStyle);
            }

            for (int i = startRow; i < listPriceNotArticleEntityList.size() + startRow; i++) {
                Row rowData = format.createRow(i);

                Cell cellData = rowData.createCell(0);
                cellData.setCellValue(listPriceNotArticleEntityList.get(i - startRow).getCodart());

                cellData = rowData.createCell(1);
                cellData.setCellValue(listPriceNotArticleEntityList.get(i - startRow).getDescri());

                cellData = rowData.createCell(2);
                cellData.setCellValue(0.0);
                cellData.setCellStyle(priceCellStyle);

                cellData = rowData.createCell(3);
                cellData.setCellValue("N");

                cellData = rowData.createCell(4);
                cellData.setCellValue("N");

                cellData = rowData.createCell(5);
                cellData.setCellValue(100.0);

                cellData = rowData.createCell(6);
                cellData.setCellValue(0.0);
                cellData.setCellStyle(descCellStyle);

                cellData = rowData.createCell(7);
                cellData.setCellValue(0.0);
                cellData.setCellStyle(descCellStyle);

                cellData = rowData.createCell(8);
                cellData.setCellValue(0.0);
                cellData.setCellStyle(descCellStyle);

                cellData = rowData.createCell(9);
                cellData.setCellValue(0.0);
                cellData.setCellStyle(descCellStyle);
            }

            return xssfWorkbook;
        }
        catch ( FileNotFoundException e ) {
            throw new GenericByteServiceException("REPORTED NOT IMPLEMENT",e.getMessage(),e);
        } catch ( IOException e ) {
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    public ApiResponseReport<?> importXSSFWorkbook(Integer codlistprice, XSSFWorkbook xssfWorkbook) throws IOException, JRException, GenericByteServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        String coduser = securityContextInitializer.getCodUser();
        List<ColumnInfo> columnInfoList = readHeaderXSSFWorkbook(xssfWorkbook);
        List<ListPriceArticleEntity> listPriceArticleEntityList = new ArrayList<>();
        XSSFSheet formatSheet = xssfWorkbook.getSheet(IMPORT_SHEET_PRINCIPAL);
        List<ImportErrorModel> importErrorModelListGeneral = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int startRow = IMPORT_START_ROW;
        int lastRow = formatSheet.getLastRowNum();
        for (int i = startRow; i <= lastRow; i++) {
            ListPriceArticleEntity listPriceArticleEntity = new ListPriceArticleEntity();

            Row row = formatSheet.getRow(i);

            // Validate Required
            List<ImportErrorModel> importErrorModelList = validateFieldRequiredXSSFWorkbook(columnInfoList, row);
            importErrorModelListGeneral.addAll(importErrorModelList);

            // Register
            listPriceArticleEntity.setIdcompany(idcompany);
            listPriceArticleEntity.setCodlistprice(codlistprice);
            listPriceArticleEntity.setCodart(getStringCellValue(row.getCell(0)));
            listPriceArticleEntity.setDesart(getStringCellValue(row.getCell(1)));
            listPriceArticleEntity.setPrice(getBigDecimalCellValue(row.getCell(2)));
            listPriceArticleEntity.setModprice(getStringCellValue(row.getCell(3)));
            listPriceArticleEntity.setModdesc(getStringCellValue(row.getCell(4)));
            listPriceArticleEntity.setDesmax(getBigDecimalCellValue(row.getCell(5)));
            listPriceArticleEntity.setDesc01(getBigDecimalCellValue(row.getCell(6)));
            listPriceArticleEntity.setDesc02(getBigDecimalCellValue(row.getCell(7)));
            listPriceArticleEntity.setDesc03(getBigDecimalCellValue(row.getCell(8)));
            listPriceArticleEntity.setDesc04(getBigDecimalCellValue(row.getCell(9)));
            listPriceArticleEntity.setImpigv(BigDecimal.ZERO);
            listPriceArticleEntity.setImpisc(BigDecimal.ZERO);
            listPriceArticleEntity.setImptribadd01(BigDecimal.ZERO);
            listPriceArticleEntity.setImptribadd02(BigDecimal.ZERO);
            listPriceArticleEntity.setImptribadd03(BigDecimal.ZERO);
            listPriceArticleEntity.setImptribadd04(BigDecimal.ZERO);
            listPriceArticleEntity.setImplistprice(listPriceArticleEntity.getPrice());
            BigDecimal totalDiscount = listPriceArticleEntity.getDesc01().add(listPriceArticleEntity.getDesc02()).add(listPriceArticleEntity.getDesc03()).add(listPriceArticleEntity.getDesc04());
            listPriceArticleEntity.setImpdesctotal(listPriceArticleEntity.getPrice().multiply(totalDiscount));
            listPriceArticleEntity.setImpsaleprice(listPriceArticleEntity.getPrice().subtract(listPriceArticleEntity.getImpdesctotal()));
            listPriceArticleEntity.setImptribtotal(BigDecimal.ZERO);
            listPriceArticleEntity.setImptotal(listPriceArticleEntity.getImpsaleprice().subtract(listPriceArticleEntity.getImptribtotal()));
            listPriceArticleEntity.setStatus("Y");
            listPriceArticleEntity.setCreateat(LocalDateTime.now());
            listPriceArticleEntity.setCreateby(coduser);
            listPriceArticleEntity.setUpdateat(LocalDateTime.now());
            listPriceArticleEntity.setUpdateby(coduser);
            listPriceArticleEntityList.add(listPriceArticleEntity);
        }
        if (!importErrorModelListGeneral.isEmpty()){
            Integer numberRow = lastRow - (startRow - 1);
            return this.genericReport.genericResponseReport(importErrorModelListGeneral,TITLE_PRICE_LIST_ARTICLE, numberRow);
        }
        return validateAndImportList(listPriceArticleEntityList, startRow, lastRow);
    }

    public ApiResponseReport<?> validateAndImportList(List<ListPriceArticleEntity> listPriceArticleEntityList, Integer startRow, Integer lastRow) throws JRException, GenericByteServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<ImportErrorModel> importErrorModelList = new ArrayList<>();
        Integer currentRow = startRow;
        for (ListPriceArticleEntity entity : listPriceArticleEntityList) {
            currentRow = currentRow + 1;
            if(!this.articleRepository.existsById(new ArticlePK(idcompany,entity.getCodart()))){
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow,entity.getCodart(),"ARTICLE NOT EXISTS");
                importErrorModelList.add(importErrorModel);
            }
            if(entity.getPrice().compareTo(new BigDecimal(0)) <= 0){
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow,entity.getPrice().toString(),"PRICE NOT ZERO OR NEGATIVE VALUES");
                importErrorModelList.add(importErrorModel);
            }
            if(!VALUE_YES_OR_NOT.contains(entity.getModprice())) {
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow,entity.getModprice(),"MODIFY THE PRICE ONLY ADMITS VALUES Y OR N");
                importErrorModelList.add(importErrorModel);
            }
            if(!VALUE_YES_OR_NOT.contains(entity.getModdesc())) {
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow,entity.getModdesc(),"MODIFY THE DISCOUNT ONLY ADMITS VALUES Y OR N");
                importErrorModelList.add(importErrorModel);
            }
            if(entity.getDesmax().compareTo(new BigDecimal(99)) > 0 || entity.getDesmax().compareTo(new BigDecimal(0)) < 0 ){
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow,entity.getDesmax().toString(),"DISCOUNT MAXIMUS BETWEEN VALUES IN 99 AND 0");
                importErrorModelList.add(importErrorModel);
            }
            BigDecimal totalDiscount = entity.getDesc01().add(entity.getDesc02()).add(entity.getDesc03()).add(entity.getDesc04());
            if(totalDiscount.compareTo(entity.getDesmax()) > 0){
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow,totalDiscount.toString(),"TOTAL DISCOUNT IS GREATER THAN MAXIMUM ALLOWED DISCOUNT");
                importErrorModelList.add(importErrorModel);
            }
        }
        Integer numberRow = lastRow - (startRow - 1);
        if (importErrorModelList.isEmpty()){
            this.listPriceArticleRepository.saveAll(listPriceArticleEntityList);
        }
        return this.genericReport.genericResponseReport(importErrorModelList,TITLE_PRICE_LIST_ARTICLE, numberRow);
    }

}
