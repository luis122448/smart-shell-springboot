package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.primary.UserKey;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserEntity")
@IdClass(UserKey.class)
@Table(schema = "smart_shell", name = "TBL_USER")
public class UserEntity {
    @Id
    private Integer idcompany;
    @Id
    private String coduser;
    private String role;
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
