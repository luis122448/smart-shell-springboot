package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "smart_shell", name = "TBL_COMPANY_INFO")
public class CompanyInfoEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    private String company;
    private String appellation;
    private String addres;
    private String poscod;
    private byte[] image;
    private byte[] icon;
    private byte[] logo;
    private byte[] background;
    private byte[] gloss;
    private String commen;
    private String observ;
}
