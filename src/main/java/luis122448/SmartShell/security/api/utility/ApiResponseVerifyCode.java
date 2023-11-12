package luis122448.SmartShell.security.api.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseVerifyCode {
	private Short status;
    private String message;
    private String coduser;
    private String verifyCode;
}
