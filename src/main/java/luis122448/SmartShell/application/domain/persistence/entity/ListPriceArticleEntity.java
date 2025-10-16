package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ListPriceArticlePK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@IdClass(ListPriceArticlePK.class)
@Table(schema = "smart_shell",name = "TBL_LIST_PRICE_ARTICLE")
public class ListPriceArticleEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    private Integer codlistprice;
    @Id
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
}
