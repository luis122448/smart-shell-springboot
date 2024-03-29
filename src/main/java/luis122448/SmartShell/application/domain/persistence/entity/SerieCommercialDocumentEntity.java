package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.SerieCommercialDocumentPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(SerieCommercialDocumentPK.class)
@Table(schema = "smart_shell", name= "TBL_SERIE_COMMERCIAL_DOCUMENT")
@Entity
public class SerieCommercialDocumentEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
	@Id
    private Integer typcomdoc;
    @Id
    private String serie;
    private String abrevi;
    private String descri;
    private String codext;
    private Integer codbranch;
    private String docelectr;
    private String typcorrel;
    private Long nrocorrel;
    private Integer typformat;
    private String observ;
    private String commen;
    private String defaul;

}
