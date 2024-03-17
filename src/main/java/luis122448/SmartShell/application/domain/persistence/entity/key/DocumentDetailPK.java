package luis122448.SmartShell.application.domain.persistence.entity.key;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDetailPK implements Serializable {
    private Integer idcompany;
    private Long numint;
    private Long numite;

}
