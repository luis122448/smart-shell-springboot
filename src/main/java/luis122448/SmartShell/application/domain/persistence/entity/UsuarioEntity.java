package luis122448.SmartShell.application.domain.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(schema = "smart_shell", name= "TBL_USER")
@Entity
public class UsuarioEntity {
	@Id
    private String coduser;
    private String encode;
    private String codrol;
    private String codver;
    private String apepat;
    private String apemat;
    private String nombre;
    private String direcc;
    private String telefono;
    private String email;
    private LocalDate fregis;
    private LocalDate fexpir;
    private byte[] image;
    private String observ;
    private String glosa;
    private String estado;
    private String ucreac;
    private String uactua;
    private LocalDateTime fcreac;
    private LocalDateTime factua;
}
