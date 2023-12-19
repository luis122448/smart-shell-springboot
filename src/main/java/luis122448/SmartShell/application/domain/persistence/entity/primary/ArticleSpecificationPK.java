package luis122448.SmartShell.application.domain.persistence.entity.primary;

import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleSpecificationPK implements Serializable {
    private Integer typinv;
    private Integer typspe;
}
