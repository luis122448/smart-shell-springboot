package luis122448.SmartShell.application.domain.domain.report.service;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.model.DocumentGenericPrintDTO;
import luis122448.SmartShell.application.domain.domain.report.constant.DIRECTORYConstants;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentHeaderService;
import luis122448.SmartShell.application.domain.persistence.mapper.DocumentGenericMapper;
import luis122448.SmartShell.util.exception.GenericListServiceException;
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

@Slf4j
@Service
public class DocumentInternalGuideReport {

    private final DocumentGenericMapper documentGenericMapper;
    private final DocumentHeaderService documentHeaderService;

    public DocumentInternalGuideReport(DocumentGenericMapper documentGenericMapper, DocumentHeaderService documentHeaderService) {
        this.documentGenericMapper = documentGenericMapper;
        this.documentHeaderService = documentHeaderService;
    }

    public ApiResponseReport<?> printDocument(Long numint) throws GenericListServiceException, JRException, FileNotFoundException {
        List<DocumentGenericPrintDTO> obj = this.documentGenericMapper.toListDocumentGenericPrintDTO(this.documentHeaderService.printDocumentGeneric(numint).getList().orElseThrow());
        JRDataSource dataSource = new JRBeanCollectionDataSource(obj);
        JasperPrint jasperPrint = JasperFillManager.fillReport(DIRECTORYConstants.getJasperInputStream(REPORT_INVOICE_A4_VERTICAL),new HashMap<>(), dataSource);
        return new ApiResponseReport<>(Optional.of(jasperPrint));
    }

}
