package luis122448.SmartShell.application.domain.persistence.entity.key;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentTransactionPK implements Serializable {
    private Integer idcompany;
    private Integer index;
    private Long numint;
}
