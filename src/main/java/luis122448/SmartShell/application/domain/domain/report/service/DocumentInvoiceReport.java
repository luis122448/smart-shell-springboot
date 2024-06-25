package luis122448.SmartShell.application.domain.domain.report.service;

import luis122448.SmartShell.application.domain.domain.model.DocumentInvoicePrintDTO;
import luis122448.SmartShell.application.domain.domain.report.constant.DIRECTORYConstants;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.domain.usecase.DocumentInvoiceUseCase;
import luis122448.SmartShell.util.object.api.ApiResponseReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static luis122448.SmartShell.application.domain.domain.report.constant.DIRECTORYConstants.*;
@Service
public class DocumentInvoiceReport {

    private final DocumentInvoiceUseCase documentInvoiceUseCase;
    public DocumentInvoiceReport(DocumentInvoiceUseCase documentInvoiceUseCase) {
        this.documentInvoiceUseCase = documentInvoiceUseCase;
    }

    public ApiResponseReport<?> invoicePrintDocument(Long numint) throws GenericListServiceException, JRException, FileNotFoundException {
        List<DocumentInvoicePrintDTO> obj = this.documentInvoiceUseCase.printDocument(numint).getList().orElseThrow();
        JRDataSource dataSource = new JRBeanCollectionDataSource(obj);
        String report = "";
        Integer typformat = obj.get(0).getTypformat();
        Integer typcomdoc = obj.get(0).getTypcomdoc();
        if (typcomdoc == 1 && typformat == 1 ) {
            report = REPORT_INVOICE_A4_HORIZONTAL;
        } else if (typcomdoc == 1 && typformat == 2) {
            report = REPORT_INVOICE_A4_VERTICAL;
        } else if (typcomdoc == 2 && typformat == 1) {
            report = REPORT_RECEIPT_A4_HORIZONTAL;
        } else if (typcomdoc == 2 && typformat == 2) {
            report = REPORT_RECEIPT_A4_VERTICAL;
        } else {
            report = REPORT_INVOICE_A4_HORIZONTAL;
        }
        System.out.println(report);
        JasperPrint jasperPrint = JasperFillManager.fillReport(DIRECTORYConstants.getJasperInputStream(report),new HashMap<>(), dataSource);
        return new ApiResponseReport<>(1,"Ok", Optional.of(jasperPrint));
    }
}
