package luis122448.SmartShell.application.domain.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ListPriceArticleDTO {
    private Integer codlistprice;
    private String codart;
    private String desart;
    private BigDecimal price;
    private String modprice;
    private String moddesc;
    private BigDecimal desmax;
    private BigDecimal desc01;
    private BigDecimal desc02;
    private BigDecimal desc03;
    private BigDecimal desc04;
    private BigDecimal impigv;
    private BigDecimal impisc;
    private BigDecimal imptribadd01;
    private BigDecimal imptribadd02;
    private BigDecimal imptribadd03;
    private BigDecimal imptribadd04;
    private BigDecimal implistprice;
    private BigDecimal impdesctotal;
    private BigDecimal impsaleprice;
    private BigDecimal imptribtotal;
    private BigDecimal imptotal;
    private String status;
}
