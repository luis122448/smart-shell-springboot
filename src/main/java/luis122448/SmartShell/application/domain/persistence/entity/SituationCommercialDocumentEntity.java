package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.SituationCommercialDocumentPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SituationCommercialDocumentPK.class)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "smart_shell", name = "TBL_SITUATION_COMMERCIAL_DOCUMENT")
public class SituationCommercialDocumentEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    public Integer typcomdoc;
    @Id
    public Integer sitcomdoc;
    public String abrevi;
    public String descri;
    public String codext;
    public String observ;
    public String commen;
}
