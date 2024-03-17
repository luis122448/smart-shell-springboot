package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.TypePaymentConditionPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(TypePaymentConditionPK.class)
@Table(schema = "smart_shell", name= "TBL_TYPE_PAYMENT_CONDITION")
@Entity
public class TypePaymentConditionEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
	@Id
    private Short typpaycon;
    private String abrevi;
    private String descri;
    private String codext;
    private String observ;
    private String commen;

}
