package luis122448.SmartShell.application.domain.persistence.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BusparPaymentConditionPagoPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(BusparPaymentConditionPagoPK.class)
@Table(schema = "smart_shell", name= "TBL_BUSPAR_PAYMENT_CONDITION")
@Entity
public class BusparPaymentConditionEntity extends AuditingEntity {
	@Id
    private String codbuspar;
    @Id
	private Short typpaycon;
    private BigDecimal limcre;
}
