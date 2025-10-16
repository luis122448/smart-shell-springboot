package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.TypeInventoryPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(TypeInventoryPK.class)
@Table(schema = "smart_shell", name= "TBL_TYPE_INVENTORY")
@Entity
public class TypeInventoryEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    private Integer typinv;
    private String abrevi;
    private String descri;
    private String codext;
    private String observ;
    private String commen;
    private String defaul;

}
