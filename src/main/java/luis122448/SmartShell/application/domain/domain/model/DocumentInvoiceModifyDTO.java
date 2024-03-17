package luis122448.SmartShell.application.domain.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoiceDetailModify;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentInvoiceModifyDTO {
    private DocumentHeaderDTO header;
    private List<DocumentInvoiceDetailModifyDTO> details;
}
