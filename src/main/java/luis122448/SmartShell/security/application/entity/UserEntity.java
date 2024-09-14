package luis122448.SmartShell.security.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.security.application.entity.primary.UserKey;

import java.io.Serializable;
import java.time.LocalDate;

import static luis122448.SmartShell.security.application.entity.constant.DatabaseConstant.SCHEMA_SMART_SHELL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserKey.class)
@Entity(name = "UserEntitySecurity")
@Table(schema = SCHEMA_SMART_SHELL, name = "TBL_USER")
public class UserEntity implements Serializable {
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
    private byte[] image;
    private byte[] gloss;
}
