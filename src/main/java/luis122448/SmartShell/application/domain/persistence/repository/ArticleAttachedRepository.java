package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.ArticleAttachedEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleAttachedPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleAttachedRepository extends JpaRepository<ArticleAttachedEntity, ArticleAttachedPK> {

    @Query("SELECT a FROM ArticleAttachedEntity a WHERE a.idcompany = :idcompany AND a.codart = :codart " +
            "AND (a.status = :status OR :status = '')")
    List<ArticleAttachedEntity> findByIdcompanyAndCodart(@Param("idcompany") Integer idcompany, @Param("codart") String codart, @Param("status") String status) throws GenericListServiceException;

}
