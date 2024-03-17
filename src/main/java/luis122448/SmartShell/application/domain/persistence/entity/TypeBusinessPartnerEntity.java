package luis122448.SmartShell.application.domain.persistence.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.TypeBusinessPartnerPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(TypeBusinessPartnerPK.class)
@Table(schema = "smart_shell", name= "TBL_TYPE_BUSINESS_PARTNER")
@Entity
public class TypeBusinessPartnerEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    private Short typbuspar;
    private String abrevi;
    private String descri;
    private String codext;
    private String observ;
    private String commen;
    private String status;

}
