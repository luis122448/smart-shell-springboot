package luis122448.SmartShell.application.domain.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleAttachedPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@IdClass(ArticleAttachedPK.class)
@Entity
@Table(schema = "smartshell", name = "TBL_ARTICLE_ATTACHED")
public class ArticleAttachedEntity extends AuditingEntity {

    @Id
    private String codart;
    @Id
    private Integer typspe;
    private String archive;
    private String observ;
    private String idMongo;

}
