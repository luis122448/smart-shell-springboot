package luis122448.SmartShell.application.domain.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentGenericSearchFilterDTO {
    Integer typcomdoc;
    LocalDate startat;
    LocalDate finalat;
    String sitcomdoc;
    String reacomdoc;
    String serie;
    Integer typpaycon;
    String codbuspar;
}
