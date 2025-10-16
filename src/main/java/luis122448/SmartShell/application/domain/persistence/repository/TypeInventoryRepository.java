package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.primary.TypeInventoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

import luis122448.SmartShell.application.domain.persistence.entity.TypeInventoryEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeInventoryRepository extends JpaRepository<TypeInventoryEntity, TypeInventoryPK>{

    @Query("SELECT i FROM TypeInventoryEntity i WHERE i.idcompany = :idcompany " +
            "AND ( :status = '' OR i.status = :status )")
    List<TypeInventoryEntity> findByIdcompany(Integer idcompany, String status);
}
