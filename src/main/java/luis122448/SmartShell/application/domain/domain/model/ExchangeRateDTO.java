package luis122448.SmartShell.application.domain.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExchangeRateDTO {
    private LocalDate registdate;
    private String origen;
    private String destin;
    private BigDecimal fventa;
    private BigDecimal fcompra;
    private BigDecimal cventa;
    private BigDecimal ccompra;
    private BigDecimal eventa;
    private BigDecimal ecompra;
    private String status;
}
