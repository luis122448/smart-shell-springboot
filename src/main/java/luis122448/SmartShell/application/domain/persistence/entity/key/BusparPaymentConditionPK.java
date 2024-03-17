package luis122448.SmartShell.application.domain.persistence.entity.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusparPaymentConditionPK implements Serializable {
    private Integer idcompany;
    private String codbuspar;
	private Short typpaycon;
}
