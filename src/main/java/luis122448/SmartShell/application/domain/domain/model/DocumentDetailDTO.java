package luis122448.SmartShell.application.domain.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentDetailDTO {
    private Long numint;
    private Long numite;
    private Integer typinv;
    private String codart;
    private Long etiqueta;
    private BigDecimal quantity;
    private BigDecimal price;
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
}
