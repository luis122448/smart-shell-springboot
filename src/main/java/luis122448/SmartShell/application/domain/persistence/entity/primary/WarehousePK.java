package luis122448.SmartShell.application.domain.persistence.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehousePK implements Serializable {
    private Integer idcompany;
    private Integer typinv;
    private Integer codwarehouse;
}

