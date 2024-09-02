package luis122448.SmartShell.application.domain.persistence.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface DocumentGenericSearch {

    Long getNumint();
    Long getNumdoc();
    String getSerie();
    Integer getTypcomdoc();
    String getDestypcomdoc();
    Integer getSitcomdoc();
    String getDessitcomdoc();
    LocalDate getRegistdate();
    Integer getInout();
    String getDesinout();
    Integer getReacomdoc();
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
