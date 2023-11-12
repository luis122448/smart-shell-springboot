package luis122448.SmartShell.application.domain.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentHeaderDTO {
    private Long numint;
    private String codext;
    private Long typcomdoc;
    private Long sitcomdoc;
    private String serie;
    private Long numdoc;
    private LocalDate registdate;
    private Long codbranch;
    private Long codplaiss;
    private Long ingsalcom;
    private Long reacomdoc;
    private String codcur;
    private BigDecimal exchangerate;
    private String codbuspar;
    private String busnam;
    private String addres;
    private String poscod;
    private String codsel;
    private Long typpaycon;
    private Long incigv;
    private BigDecimal tasigv;
    private BigDecimal impafecto;
    private BigDecimal impinafecto;
    private BigDecimal impexonerado;
    private BigDecimal impgratuito;
    private BigDecimal impigv;
    private BigDecimal impisc;
    private BigDecimal imptribadd01;
    private BigDecimal imptribadd02;
    private BigDecimal imptribadd03;
    private BigDecimal imptribadd04;
    private BigDecimal impdesc01;
    private BigDecimal impdesc02;
    private BigDecimal impdesc03;
    private BigDecimal impdesc04;
    private BigDecimal implistprice;
    private BigDecimal impdesctotal;
    private BigDecimal impsaleprice;
    private BigDecimal imptribtotal;
    private BigDecimal imptotal;
    private String refere;
    private String observ;
    private String commen;
    private byte[] arcpdf;
    private byte[] arccrd;
    private byte[] arcxml;
}
