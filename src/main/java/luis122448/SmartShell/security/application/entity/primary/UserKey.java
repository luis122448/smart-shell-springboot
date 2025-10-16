package luis122448.SmartShell.security.application.entity.primary;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserKey implements Serializable {
    private Integer idcompany;
    private String coduser;
}
