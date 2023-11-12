package luis122448.SmartShell.security.api.dto;

import lombok.Data;

@Data
public class VerifyCodeRequest {
	private String coduser;
    private String codver;

}
