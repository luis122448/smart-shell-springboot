package luis122448.SmartShell.application.domain.persistence.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReasonCommercialDocumentPK implements Serializable {
    private Integer idcompany;
    private Integer typcomdoc;
    private Integer inout;
    private Integer reacomdoc;
}
