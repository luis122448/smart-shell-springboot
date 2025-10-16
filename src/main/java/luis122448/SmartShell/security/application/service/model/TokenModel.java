package luis122448.SmartShell.security.application.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenModel {
    private String coduser;
    private String role;
    private String token;
    private String refreshToken;
}
