package luis122448.SmartShell.application.domain.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class DocumentInvoiceSearchDTO {
    private Long numint;
    private Long numdoc;
    private String serie;
    private String destypcomdoc;
    private String dessitcomdoc;
    private LocalDate registdate;
    private String desingsalcom;
    private String desreacomdoc;
    private String codbuspar;
    private String busnam;
    private String addres;
    private String desplaiss;
    private String codcur;
    private String dessel;
    private String destyppaycon;
    private BigDecimal impsaleprice;
    private BigDecimal imptotal;
}
