package luis122448.SmartShell.application.domain.persistence.entity.primary;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExchangeRatePK implements Serializable {
    private LocalDate registdate;
    private String origen;
    private String destin;
}
