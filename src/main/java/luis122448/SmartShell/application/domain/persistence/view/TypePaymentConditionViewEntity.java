package luis122448.SmartShell.application.domain.persistence.view;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import org.hibernate.annotations.Immutable;

import luis122448.SmartShell.application.domain.persistence.entity.key.BusparPaymentConditionPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@EntityListeners({AuditingEntityListener.class})
@IdClass(BusparPaymentConditionPK.class)
@Table(schema = "smart_shell", name= "VW_TYPE_PAYMENT_CONDITION")
@Entity
public class TypePaymentConditionViewEntity extends AuditingEntity {
	@Id
	private Integer idcompany;
	@Id
	private String codbuspar;
	@Id
	private Short typpaycon;
    private String abrtyppaycon;
    private String destyppaycon;
    private BigDecimal limcre;
}
