package luis122448.SmartShell.security.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.security.application.entity.key.UserKey;

import java.time.LocalDate;

import static luis122448.SmartShell.security.application.entity.constant.DatabaseConstant.SCHEMA_SMART_SHELL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserEntitySecurity")
@IdClass(UserKey.class)
@Table(schema = SCHEMA_SMART_SHELL, name = "TBL_USER")
public class UserEntity {
    @Id
    private Integer idcompany;
    @Id
    private String coduser;
    private String encode;
    private String role;
    private Integer nivel;
    private String code;
    private String apepat;
    private String apemat;
    private String nombre;
    private String addres;
    private String poscod;
    private String phone;
    private String email;
    private LocalDate registdate;
    private LocalDate expiredate;
}
