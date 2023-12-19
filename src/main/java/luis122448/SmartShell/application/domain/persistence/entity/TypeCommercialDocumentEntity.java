package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@Table(schema = "smart_shell", name= "TBL_TYPE_COMMERCIAL_DOCUMENT")
@Entity
public class TypeCommercialDocumentEntity extends AuditingEntity {
	@Id
	private Long typcomdoc;
    private String abrevi;
    private String descri;
    private String codext;
    private Byte[] image;
    private String observ;
    private String commen;

}
