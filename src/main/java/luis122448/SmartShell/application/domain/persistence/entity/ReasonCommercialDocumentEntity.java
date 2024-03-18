package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ReasonCommercialDocumentPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ReasonCommercialDocumentPK.class)
@EntityListeners({AuditingEntityListener.class})
@Entity
@Table(schema = "smart_shell",name = "TBL_REASON_COMMERCIAL_DOCUMENT")
public class ReasonCommercialDocumentEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    public Integer typcomdoc;
    @Id
    public Integer ingsalcom;
    @Id
    public Integer reacomdoc;
    public String abrevi;
    public String descri;
    public String codext;
    public String observ;
    public String commen;
    public String defaul;

}
