package luis122448.SmartShell.application.domain.persistence.entity.primary;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleAttachedPK implements Serializable {

    private String codart;
    private Integer typspe;
}
