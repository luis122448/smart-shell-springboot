package luis122448.SmartShell.security.application.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyCodeModel implements Serializable {
    private String company;
    private String coduser;
    private String verifyCode;
}
