package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.ArticleSpecificationEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleSpecificationPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleSpecificationRepository extends JpaRepository<ArticleSpecificationEntity, ArticleSpecificationPK> {

    @Query("SELECT a FROM ArticleSpecificationEntity a WHERE a.idcompany = :idcompany AND a.typinv = :typinv " +
            "AND (a.status = :status OR :status = '')")
    List<ArticleSpecificationEntity> findByIdcompanyAndTypinv(@Param("idcompany") Integer idcompany,
                                                              @Param("typinv") Integer typinv,
                                                              @Param("status") String status) throws GenericListServiceException;

}
