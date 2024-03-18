package luis122448.SmartShell.application.domain.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BusinessPartnerPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(BusinessPartnerPK.class)
@Table(schema = "smart_shell", name= "TBL_BUSINESS_PARTNER")
@Entity
public class BusinessPartnerEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
	@Id
    private String codbuspar;
    private Short typbuspar;
    private Short typidedoc;
    private String nroidedoc;
    private String codext;
    private String busnam;
    private String apepat;
    private String apemat;
    private String nombre;
    private LocalDate registdate;
    private String addres;
    private String poscod;
    private String codtel;
    private String telefo;
    private String email;
    private Short typpaycon;
    private BigDecimal limcre;
    private Short codlistprice;
    private byte[] image;
    private String observ;
    private String commen;

}