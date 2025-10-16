package luis122448.SmartShell.application.domain.persistence.repository;

import java.util.List;

import luis122448.SmartShell.application.domain.persistence.entity.BusinessPartnerEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticlePK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, ArticlePK>{
	
    @Query("SELECT a FROM ArticleEntity a WHERE a.idcompany = :idcompany AND a.codart LIKE CONCAT('%', :codart, '%') AND a.status = 'Y'")
    List<ArticleEntity> findByCodart(@Param("idcompany") Integer idcompany, @Param("codart") String codart);

    @Query("SELECT a FROM ArticleEntity a WHERE a.idcompany = :idcompany AND UPPER(a.descri) LIKE CONCAT('%', UPPER(:descri), '%') AND a.status = 'Y'")
    List<ArticleEntity> findByDescri(@Param("idcompany") Integer idcompany, @Param("descri") String descri);

    @Query("SELECT a FROM ArticleEntity a WHERE a.idcompany = :idcompany AND UPPER(CONCAT(a.codart ,a.descri)) LIKE CONCAT('%', UPPER(:name), '%') AND a.status = 'Y' O" +
            "RDER BY a.codart ASC LIMIT 25")
    List<ArticleEntity> findByDName(@Param("idcompany") Integer idcompany, @Param("name") String name);

	@Query("SELECT a FROM ArticleEntity a WHERE"
            + " a.idcompany = :idcompany"
            + " AND ( :typinv = -1 OR a.typinv = :typinv )"
            + " AND UPPER(a.codart) LIKE CONCAT('%', :codart, '%')"
            + " AND UPPER(a.descri) LIKE CONCAT('%', UPPER(:descri), '%')"
            + " AND ( :status = '' OR a.status = :status )")
	Page<ArticleEntity> findByPage(@Param("idcompany") Integer idcompany, @Param("typinv") Integer typinv, @Param("codart") String codart, @Param("descri") String descri, @Param("status") String status, Pageable pageable);

    @Query(value = "SELECT a FROM ArticleEntity a WHERE a.idcompany = :idcompany AND a.codart NOT IN (SELECT l.codart FROM ListPriceArticleEntity l WHERE l.codlistprice = :codlistprice) AND a.status = 'Y'")
    List<ArticleEntity> findByArticleNotExistsListPrice(@Param("idcompany") Integer idcompany, @Param(value = "codlistprice") Integer codlistprice) throws GenericListServiceException;

    List<ArticleEntity> findByIdcompanyAndTypinv(@Param("idcompany") Integer idcompany, @Param("typinv") Integer typinv);

}
