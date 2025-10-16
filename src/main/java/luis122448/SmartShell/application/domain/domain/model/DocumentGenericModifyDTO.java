package luis122448.SmartShell.application.domain.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentGenericModifyDTO {
    private DocumentHeaderDTO header;
    private List<DocumentGenericDetailModifyDTO> details;
}
