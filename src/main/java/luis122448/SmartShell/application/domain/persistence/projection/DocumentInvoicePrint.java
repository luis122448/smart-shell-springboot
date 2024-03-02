package luis122448.SmartShell.application.domain.persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

//@Getter
//@Setter
//@AllArgsConstructor
//public class DocumentInvoicePrint{
//
//    private Long numint;
//    private Long numdoc;
//    private String serie;
//    private String desdoccom;
//    private String dessitcom;
//    private LocalDate fregis;
//    private String desingsalcom;
//    private String desmotcom;
//    private String codbuspar;
//    private String razsoc;
//    private String desplaiss;
//    private String codmon;
//    private String dessel;
//    private String despaycon;
//    private BigDecimal imppreven;
//    private BigDecimal impdesctotal;
//    private BigDecimal impsubtotal;
//    private BigDecimal imptribtotal;
//    private BigDecimal imptotal;
//    private Long numite;
//    private String typinv;
//    private String destypinv;
//    private String codart;
//    private String desart;
//    private Long etiqueta;
//    private String desetiqueta;
//    private String cantid;
//    private String price;
//    private BigDecimal detpreven;
//    private BigDecimal detdesctotal;
//    private BigDecimal detsubtotal;
//    private BigDecimal dettribtotal;
//    private BigDecimal dettotal;
//
//}

public interface DocumentInvoicePrint {

    Integer getTypformat();
    Integer getTypcomdoc();
    String getComdestypidedoc();
    String getComnroidedoc();
    String getComnam();
    String getComaddres();
    String getComposcod();
    byte[] getComgloss();
    Long getNumint();
    Long getNumdoc();
    String getSerie();
    String getDesdoccom();
    String getDessitcomdoc();
    LocalDate getRegistDate();
    String getDesingsalcom();
    String getDesreacomdoc();
    String getCodbuspar();
    String getBusnam();
    String getDestypidedoc();
    String getNroidedoc();
    String getAddres();
    String getPosCod();
    String getDesplaiss();
    String getCodcur();
    String getCursymbol();
    BigDecimal getExchangerate();
    String getDessel();
    String getDespaycon();
    String getRefere();
    String getObserv();
    String getCommen();
    BigDecimal getImplistprice();
    BigDecimal getImpdesctotal();
    BigDecimal getImpsaleprice();
    BigDecimal getImptribtotal();
    BigDecimal getImptotal();
    String getImpname();
    Long getNumite();
    Integer getTypinv();
    String getDestypinv();
    String getCodart();
    String getDesart();
    Long getEtiqueta();
    String getDesetiqueta();
    BigDecimal getQuantity();
    BigDecimal getPrice();
    BigDecimal getDetlistprice();
    BigDecimal getDetdesctotal();
    BigDecimal getDetsaleprice();
    BigDecimal getDettribtotal();
    BigDecimal getDettotal();

}
