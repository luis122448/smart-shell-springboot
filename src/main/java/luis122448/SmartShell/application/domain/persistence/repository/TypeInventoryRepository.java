package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.primary.TypeInventoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

import luis122448.SmartShell.application.domain.persistence.entity.TypeInventoryEntity;

import java.util.List;

public interface TypeInventoryRepository extends JpaRepository<TypeInventoryEntity, TypeInventoryPK>{
    List<TypeInventoryEntity> findByIdcompany(Integer idcompany);
}
