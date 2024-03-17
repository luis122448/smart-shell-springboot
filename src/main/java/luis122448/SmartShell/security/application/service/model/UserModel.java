package luis122448.SmartShell.security.application.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class UserModel {
    private Integer idcompany;
    private String company;
    private String appellation;
    private String coduser;
    private String username;
    private String encode;
    private Integer nivel;
    private String code;
    private String role;
    private LocalDate registdate;
    private LocalDate expiredate;
    private String status;

}
