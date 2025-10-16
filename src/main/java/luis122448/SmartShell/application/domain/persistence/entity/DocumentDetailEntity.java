package luis122448.SmartShell.application.domain.persistence.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.DocumentDetailPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(DocumentDetailPK.class)
@Table(schema = "smart_shell",name = "TBL_DOCUMENT_DETAIL")
@Entity
public class DocumentDetailEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
    @Id
    private Long numint;
    @Id
    private Long numite;
    private Integer typinv;
    private String codart;
    private Long etiqueta;
    private BigDecimal quantity;
    private BigDecimal price;
    private LocalDate registdate;
    private Integer codbranch;
    private Integer oriwarehouse;
    private Integer deswarehouse;
    private BigDecimal impafecto;
    private BigDecimal impinafecto;
    private BigDecimal impexonerado;
    private BigDecimal impgratuito;
    private BigDecimal impigv;
    private BigDecimal impisc;
    private BigDecimal imptribadd01;
    private BigDecimal imptribadd02;
    private BigDecimal imptribadd03;
    private BigDecimal imptribadd04;
    private BigDecimal impdesc01;
    private BigDecimal impdesc02;
    private BigDecimal impdesc03;
    private BigDecimal impdesc04;
    private BigDecimal implistprice;
    private BigDecimal impdesctotal;
    private BigDecimal impsaleprice;
    private BigDecimal imptribtotal;
    private BigDecimal imptotal;

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(this);
    }
}
