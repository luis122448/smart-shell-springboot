package luis122448.SmartShell.application.domain.persistence.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.SellerPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(SellerPK.class)
@Table(schema = "smart_shell", name= "TBL_SELLER")
@Entity
public class SellerEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    private String codsel;
    private String abrevi;
    private String descri;
    private String apepat;
    private String apemat;
    private String nombre;
    private LocalDate registdate;
    private String poscod;
    private String addres;
    private String telefo;
    private String email;
    private String fax;
    private byte[] image;
    private String observ;
    private String commen;

}
