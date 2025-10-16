package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.DocumentTransactionPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@IdClass(DocumentTransactionPK.class)
@Entity
@Table(schema = "smart_shell", name = "TBL_DOCUMENT_TRANSACTION")
public class DocumentTransactionEntity extends AuditingEntity{

    @Id
    private Integer idcompany;
    @Id
    private Integer index;
    @Id
    private Long numint;
    private Integer typcomdoc;
    private Integer sitcomdoc;
    private String serie;
    private Long numdoc;
    private LocalDate registdate;
    private Integer codbranch;
    private Integer codplaiss;
    private Integer inout;
    private Integer reacomdoc;
    private String busnam;
    private String addres;
    private String poscod;
    private String codcur;
    private BigDecimal exchangerate;
    private Integer incigv;
    private BigDecimal tasigv;
    private String codsel;
    private Integer typpaycon;
    private BigDecimal implistprice;
    private BigDecimal impdesctotal;
    private BigDecimal impsaleprice;
    private BigDecimal imptotal;
    private BigDecimal imptribtotal;
    private String observ;
    private String commen;

}
