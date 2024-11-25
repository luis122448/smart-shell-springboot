package luis122448.SmartShell.application.domain.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BusparPaymentConditionDTO {
    private String codbuspar;
    private Short typpaycon;
    private BigDecimal limcre;
    private String status;
}
