package luis122448.SmartShell.application.domain.persistence.entity.key;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRatePK implements Serializable {
    private Integer idcompany;
    private LocalDate registdate;
    private String origen;
    private String destin;
}
