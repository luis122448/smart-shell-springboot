package luis122448.SmartShell.application.domain.domain.report.constant;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DIRECTORYConstants {

    public static Path projectRoot = Paths.get(System.getProperty("user.dir")).toAbsolutePath();
    public static final Path REPORT_DIRECTORY = projectRoot.resolve("src/main/resources/archive/jasper/");
    public static final Path EXCEL_DIRECTORY = projectRoot.resolve("src/main/resources/archive/excel/");

    public static final String REPORT_INVOICE_A4_HORIZONTAL = REPORT_DIRECTORY.resolve("invoice-a4-horizontal.jasper").toString();
    public static final String REPORT_INVOICE_A4_VERTICAL = REPORT_DIRECTORY.resolve("invoice-a4-vertical.jasper").toString();
    public static final String REPORT_RECEIPT_A4_HORIZONTAL = REPORT_DIRECTORY.resolve("receipt-a4-horizontal.jasper").toString();
    public static final String REPORT_RECEIPT_A4_VERTICAL = REPORT_DIRECTORY.resolve("receipt-a4-vertical.jasper").toString();
    public static final String REPORT_ERROR_IMPORT_A4_HORIZONTAL = REPORT_DIRECTORY.resolve("error-import-a4-horizontal.jasper").toString();
    public static final String REPORT_SUCCESS_IMPORT_A4_HORIZONTAL = REPORT_DIRECTORY.resolve("success-import-a4-horizontal.jasper").toString();
    public static final String FORMAT_PRICE_LIST_ARTICLE = EXCEL_DIRECTORY.resolve("list-price-article.xlsx").toString();
    public static final String FORMAT_ARTICLE = EXCEL_DIRECTORY.resolve("format-article.xlsx").toString();

}
