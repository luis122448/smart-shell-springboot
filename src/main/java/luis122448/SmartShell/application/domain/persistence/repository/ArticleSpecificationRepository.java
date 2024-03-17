package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.ArticleSpecificationEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.ArticleSpecificationPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleSpecificationRepository extends JpaRepository<ArticleSpecificationEntity, ArticleSpecificationPK> {

    List<ArticleSpecificationEntity> findByIdcompanyAndTypinv(@Param("idcompany") Integer idcompany, @Param("typinv") Integer typinv) throws GenericListServiceException;

}
