package luis122448.SmartShell.application.domain.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class DocumentGenericPrintDTO {

    private String format;
    private String comdestypidedoc;
    private String comnroidedoc;
    private String comnam;
    private String comaddres;
    private String composcod;
    private InputStream comgloss;
    private Long numint;
    private Long numdoc;
    private String serie;
    private String desdoccom;
    private String dessitcomdoc;
    private LocalDate registdate;
    private String desinout;
    private String desreacomdoc;
    private String codbuspar;
    private String destypidedoc;
    private String nroidedoc;
    private String busnam;
    private String addres;
    private String poscod;
    private String desplaiss;
    private String codcur;
    private String cursymbol;
    private BigDecimal exchangerate;
    private String dessel;
    private String despaycon;
    private String refere;
    private String observ;
    private String commen;
    private BigDecimal implistprice;
    private BigDecimal impdesctotal;
    private BigDecimal impsaleprice;
    private BigDecimal imptribtotal;
    private BigDecimal imptotal;
    private String impname;
    private Long numite;
    private Integer typinv;
    private String destypinv;
    private String codart;
    private String desart;
    private Long etiqueta;
    private String desetiqueta;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal detlistprice;
    private BigDecimal detdesctotal;
    private BigDecimal detsaleprice;
    private BigDecimal dettribtotal;
    private BigDecimal dettotal;
}
