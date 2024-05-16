package luis122448.SmartShell.application.domain.domain.report.constant;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DIRECTORYConstants {

//    public static Path projectRoot = Paths.get(System.getProperty("user.dir"));
    public static final String projectRoot = "./home/app/";
    public static final String REPORT_DIRECTORY = projectRoot + "src/main/resources/archive/jasper/";
    public static final String EXCEL_DIRECTORY = projectRoot + "src/main/resources/archive/excel/";

    public static final String REPORT_INVOICE_A4_HORIZONTAL = REPORT_DIRECTORY + "invoice-a4-horizontal.jasper";
    public static final String REPORT_INVOICE_A4_VERTICAL = REPORT_DIRECTORY + "invoice-a4-vertical.jasper";
    public static final String REPORT_RECEIPT_A4_HORIZONTAL = REPORT_DIRECTORY + "receipt-a4-horizontal.jasper";
    public static final String REPORT_RECEIPT_A4_VERTICAL = REPORT_DIRECTORY + "receipt-a4-vertical.jasper";
    public static final String REPORT_ERROR_IMPORT_A4_HORIZONTAL = REPORT_DIRECTORY + "error-import-a4-horizontal.jasper";
    public static final String REPORT_SUCCESS_IMPORT_A4_HORIZONTAL = REPORT_DIRECTORY + "success-import-a4-horizontal.jasper";
    public static final String FORMAT_PRICE_LIST_ARTICLE = EXCEL_DIRECTORY + "list-price-article.xlsx";
    public static final String FORMAT_ARTICLE = EXCEL_DIRECTORY + "format-article.xlsx";
}
