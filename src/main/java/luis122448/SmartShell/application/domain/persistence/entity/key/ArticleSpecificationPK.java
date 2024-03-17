package luis122448.SmartShell.application.domain.persistence.entity.key;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSpecificationPK implements Serializable {
    private Integer idcompany;
    private Integer typinv;
    private Integer typspe;
}
