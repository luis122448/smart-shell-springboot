package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.BranchEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BranchPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends JpaRepository<BranchEntity, BranchPK> {
    @Query("SELECT b FROM BranchEntity b WHERE b.idcompany = :idcompany " +
            "AND ( b.status = :status OR :status = '' )")
    List<BranchEntity> findByIdcompany(@Param("idcompany") Integer idcompany,@Param("status") String status);

    @Query("SELECT b FROM BranchEntity b WHERE b.idcompany = :idcompany " +
            "AND b.codbranch = :codbranch " +
            "AND ( b.status = :status OR :status = '' )")
    BranchEntity findByIdcompanyAndCodbranch(@Param("idcompany") Integer idcompany,@Param("codbranch") Integer codbranch,@Param("status") String status);
}
