package luis122448.SmartShell.application.domain.persistence.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPriceArticlePK implements Serializable {
    private Integer idcompany;
    private Integer codlistprice;
    private String codart;
}

