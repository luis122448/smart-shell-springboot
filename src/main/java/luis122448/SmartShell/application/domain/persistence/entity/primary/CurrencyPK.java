package luis122448.SmartShell.application.domain.persistence.entity.primary;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPK {
    @Id
    private Integer idcompany;
    @Id
    private String codcur;
}
