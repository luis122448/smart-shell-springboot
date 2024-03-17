package luis122448.SmartShell.application.domain.persistence.entity.key;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAttachedPK implements Serializable {
    private Integer idcompany;
    private String codart;
    private Integer typspe;
}
