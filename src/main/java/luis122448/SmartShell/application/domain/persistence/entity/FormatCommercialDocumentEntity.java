package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.FormatCommercialDocumentPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(FormatCommercialDocumentPK.class)
@Table(schema = "smart_shell", name= "TBL_FORMAT_COMMERCIAL_DOCUMENT")
@Entity
public class FormatCommercialDocumentEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    private Integer typcomdoc;
    @Id
    private Integer typformat;
    private String abrevi;
    private String descri;
    private String format;
    private String url;
    private byte[] image;
    private byte[] pdf;
    private String observ;
    private String commen;
    private String defaul;
    private String status;

}
