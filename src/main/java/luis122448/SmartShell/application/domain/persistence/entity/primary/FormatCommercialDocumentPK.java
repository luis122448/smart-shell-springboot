package luis122448.SmartShell.application.domain.persistence.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class FormatCommercialDocumentPK implements Serializable {
    public Integer typcomdoc;
    public Integer typformat;
}
