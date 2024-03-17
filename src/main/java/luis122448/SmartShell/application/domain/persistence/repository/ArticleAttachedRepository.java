package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.ArticleAttachedEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.ArticleAttachedPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleAttachedRepository extends JpaRepository<ArticleAttachedEntity, ArticleAttachedPK> {

    List<ArticleAttachedEntity> findByIdcompanyAndCodart(@Param("idcompany") Integer idcompany, @Param("codart") String codart) throws GenericListServiceException;

}
