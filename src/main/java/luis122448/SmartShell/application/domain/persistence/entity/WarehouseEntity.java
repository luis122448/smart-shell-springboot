package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.WarehousePK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(WarehousePK.class)
@Table(schema = "smart_shell", name= "TBL_WAREHOUSE")
@Entity
public class WarehouseEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    private Integer typinv;
    @Id
    private Integer codwarehouse;
    private String abrevi;
    private String descri;
    private String codext;
    private String observ;
    private String commen;
    private String defaul;

}
