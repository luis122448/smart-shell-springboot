package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.CurrencyPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(CurrencyPK.class)
@Table(schema = "smart_shell",name = "TBL_CURRENCY")
@Entity
public class CurrencyEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    private String codcur;
    private String abrevi;
    private String descri;
    private String codext;
    private String symbol;
    private String defaul;
}
