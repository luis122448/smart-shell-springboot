package luis122448.SmartShell.application.domain.persistence.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.primary.DocumentKardexPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(DocumentKardexPK.class)
@Table(schema = "smart_shell",name = "TBL_DOCUMENT_KARDEX")
@Entity
public class DocumentKardexEntity {
    @Id
    private Integer idcompany;
    @Id
    private Long locali;
    private Long numint;
    private Long numite;
    private Integer typinv;
    private String codart;
    private Long etiqueta;
    private BigDecimal quantity;
    private BigDecimal price;
    private Integer typdoccom;
    private Integer inout;
    private Integer reacomdoc;
    private Integer codbranch;
    private Integer oriwarehouse;
    private Integer deswarehouse;
    private LocalDate registdate;
    private Integer year;
    private Integer month;
    private BigDecimal implistprice01;
    private BigDecimal implistprice02;
    private BigDecimal impdesctotal01;
    private BigDecimal impdesctotal02;
    private BigDecimal impsaleprice01;
    private BigDecimal impsaleprice02;
    private BigDecimal imptribtotal01;
    private BigDecimal imptribtotal02;
    private BigDecimal imptotal01;
    private BigDecimal imptotal02;
    private BigDecimal cosrawmaterial01;
    private BigDecimal cosrawmaterial02;
    private BigDecimal cosmanufacturing01;
    private BigDecimal cosmanufacturing02;
    private BigDecimal coslabour01;
    private BigDecimal coslabour02;
    private BigDecimal costransport01;
    private BigDecimal costransport02;
    private BigDecimal costotal01;
    private BigDecimal costotal02;

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(this);
    }
}
