package luis122448.SmartShell.application.domain.persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

//@Getter
//@Setter
//@AllArgsConstructor
//public class DocumentInvoiceSearch {
//    private Long numint;
//    private Long numdoc;
//    private String serie;
//    private String destypcomdoc;
//    private String dessitcomdoc;
//    private LocalDate fregis;
//    private String desingsalcom;
//    private String desreacomdoc;
//    private String codbuspar;
//    private String razsoc;
//    private String direcc;
//    private String desplaiss;
//    private String codmon;
//    private String dessel;
//    private String destyppaycon;
//    private BigDecimal impsubtotal;
//    private BigDecimal imptotal;
//}

public interface DocumentInvoiceSearch {

    Long getNumint();
    Long getNumdoc();
    String getSerie();
    String getDestypcomdoc();
    String getDessitcomdoc();
    LocalDate getRegistdate();
    String getDesingsalcom();
    String getDesreacomdoc();
    String getCodbuspar();
    String getBusnam();
    String getAddres();
    String getDesplaiss();
    String getCodcur();
    String getDessel();
    String getDestyppaycon();
    BigDecimal getImpsaleprice();
    BigDecimal getImptotal();
}
