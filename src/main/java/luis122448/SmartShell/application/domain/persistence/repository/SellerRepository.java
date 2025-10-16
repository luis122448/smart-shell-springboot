package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.primary.SellerPK;
import org.springframework.data.jpa.repository.JpaRepository;

import luis122448.SmartShell.application.domain.persistence.entity.SellerEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellerRepository extends JpaRepository<SellerEntity, SellerPK>{

    @Query("SELECT s FROM SellerEntity s WHERE s.idcompany = :idcompany " +
            "AND ( :status = '' OR s.status = :status )")
    List<SellerEntity> findByIdcompany(Integer idcompany, String status);

}
