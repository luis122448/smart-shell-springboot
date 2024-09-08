package luis122448.SmartShell.application.domain.persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface DocumentKardexPrint {

    String getFormat();
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
    String getDesinout();
    String getDesreacomdoc();
    String getCodbuspar();
    String getBusnam();
    String getDestypidedoc();
    String getNroidedoc();
    String getAddres();
    String getPosCod();
    String getDesplaiss();
    String getDesoriwarehouse();
    String getDesdeswarehouse();
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
