package luis122448.SmartShell.application.domain.domain.report.service;

import luis122448.SmartShell.application.domain.domain.model.DocumentInvoicePrintDTO;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.domain.usecase.DocumentInvoiceUseCase;
import luis122448.SmartShell.util.object.api.ApiResponseReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

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


    public ApiResponseReport<?> invoiceReportA4Horizontal(Long numint) throws GenericListServiceException, JRException {
        List<DocumentInvoicePrintDTO> obj = this.documentInvoiceUseCase.printDocument(numint).getList().orElseThrow();
        JRDataSource dataSource = new JRBeanCollectionDataSource(obj);
        JasperPrint jasperPrint = JasperFillManager.fillReport(REPORT_INVOICE_A4_HORIZONTAL,new HashMap<>(), dataSource);
        return new ApiResponseReport<>(1,"Ok", Optional.of(jasperPrint));
    }
}
