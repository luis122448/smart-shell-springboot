package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.WarehouseEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.WarehousePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, WarehousePK> {

    @Query("SELECT w FROM WarehouseEntity w WHERE w.idcompany = :idcompany " +
            "AND w.typinv = :typinv " +
            "AND ( :status = '' OR w.status = :status )")
    List<WarehouseEntity> findByTypinv(Integer idcompany, Integer typinv, String status);

    @Query("SELECT w FROM WarehouseEntity w WHERE w.idcompany = :idcompany " +
            "AND ( :status = '' OR w.status = :status )")
    List<WarehouseEntity> findByAll(Integer idcompany, String status);

}
