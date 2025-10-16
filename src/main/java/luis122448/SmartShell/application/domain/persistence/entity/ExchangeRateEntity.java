package luis122448.SmartShell.application.domain.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ExchangeRatePK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(ExchangeRatePK.class)
@Table(schema = "smart_shell", name= "TBL_EXCHANGE_RATE")
@Entity
public class ExchangeRateEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
	@Id
    private LocalDate registdate;
	@Id
	private String origen;
	@Id
	private String destin;
    private BigDecimal fventa;
    private BigDecimal fcompra;
    private BigDecimal cventa;
    private BigDecimal ccompra;
    private BigDecimal eventa;
    private BigDecimal ecompra;
}
