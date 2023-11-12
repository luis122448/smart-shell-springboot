package luis122448.SmartShell.application.domain.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;

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
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(schema = "smartshell", name= "TBL_ARTICLE")
public class ArticleEntity extends AuditingEntity implements Serializable {
	@Id
    private String codart;
    private Integer typinv;
    private String abrevi;
    private String descri;
    private String codext;
    private String codbar;
    private String codean;
    private LocalDate registdate;
    private String cstock;
    private String codprv;
    private String codman;
    private String coduni;
    private String stocknegative;
    private String editdescri;
    private String printcomment;
    private byte[] image;
    private String observ;
    private String commen;
}
