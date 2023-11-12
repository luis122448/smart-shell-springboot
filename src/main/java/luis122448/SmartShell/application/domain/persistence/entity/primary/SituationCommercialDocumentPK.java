package luis122448.SmartShell.application.domain.persistence.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SituationCommercialDocumentPK implements Serializable {

    public Integer typcomdoc;
    public Integer sitcomdoc;
}
