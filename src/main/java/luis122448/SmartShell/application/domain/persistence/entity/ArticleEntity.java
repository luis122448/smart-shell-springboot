package luis122448.SmartShell.application.domain.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticlePK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@IdClass(ArticlePK.class)
@Entity
@Table(schema = "smart_shell", name= "TBL_ARTICLE")
public class ArticleEntity extends AuditingEntity implements Serializable {
    @Id
    private Integer idcompany;
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
