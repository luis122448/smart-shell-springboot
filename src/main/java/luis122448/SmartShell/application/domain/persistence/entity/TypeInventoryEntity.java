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
@Table(schema = "smartshell", name= "TBL_TYPE_INVENTORY")
@Entity
public class TypeInventoryEntity extends AuditingEntity {
    @Id
    private Integer typinv;
    private String abrevi;
    private String descri;
    private String codext;
    private String observ;
    private String commen;
    private String defaul;

}
