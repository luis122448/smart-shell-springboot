package luis122448.SmartShell.application.domain.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, String>{
	
    @Query("SELECT a FROM ArticleEntity a WHERE a.codart LIKE CONCAT('%', :codart, '%') AND a.status = 'S'")
    List<ArticleEntity> findByCodart(@Param("codart") String codart);

    @Query("SELECT a FROM ArticleEntity a WHERE UPPER(a.descri) LIKE CONCAT('%', UPPER(:descri), '%') AND a.status = 'S'")
    List<ArticleEntity> findByDescri(@Param("descri") String descri);

	@Query("SELECT a FROM ArticleEntity a WHERE a.codart LIKE CONCAT('%', :codart, '%') AND UPPER(a.descri) LIKE CONCAT('%', UPPER(:descri), '%')")
	Page<ArticleEntity> findByPage(@Param("codart") String codart, @Param("descri") String descri, Pageable pageable);

    List<ArticleEntity> findByTypinv(@Param("typinv") Integer typinv);

}
