package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BranchPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(BranchPK.class)
@Table(schema = "smart_shell", name= "TBL_BRANCH")
@Entity
public class BranchEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    private Integer codbranch;
    private String abrevi;
    private String descri;
    private String codext;
    private String addres;
    private String poscod;
    private String phone;
    private String email;
    private Integer codwarehouse;
    private byte[] glossdocument;
    private byte[] glossticket;
    private String observ;
}
