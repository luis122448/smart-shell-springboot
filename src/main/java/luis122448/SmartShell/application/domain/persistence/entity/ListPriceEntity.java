package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ListPricePK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@IdClass(ListPricePK.class)
@Entity
@Table(schema = "smart_shell",name = "TBL_LIST_PRICE")
public class ListPriceEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    private Integer codlistprice;
    private String abrevi;
    private String descri;
    private String codext;
    private String codcur;
    private String inctax;
    private String defaul;
    private String observ;
    private String commen;
}
