package luis122448.SmartShell.application.domain.domain.report.constant;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DIRECTORYConstants {

    public static Path projectRoot = Paths.get(System.getProperty("user.dir"));
    public static final String REPORT_INVOICE_A4_HORIZONTAL = projectRoot + "/src/main/resources/archive/jasper/invoice-a4-horizontal.jasper";
    public static final String REPORT_ERROR_IMPORT_A4_HORIZONTAL = projectRoot + "/src/main/resources/archive/jasper/error-import-a4-horizontal.jasper";
    public static final String REPORT_SUCCESS_IMPORT_A4_HORIZONTAL = projectRoot + "/src/main/resources/archive/jasper/success-import-a4-horizontal.jasper";
    public static final String FORMAT_PRICE_LIST_ARTICLE = projectRoot + "/src/main/resources/archive/excel/list-price-article.xlsx";
    public static final String FORMAT_ARTICLE = projectRoot + "/src/main/resources/archive/excel/format-article.xlsx";
}
