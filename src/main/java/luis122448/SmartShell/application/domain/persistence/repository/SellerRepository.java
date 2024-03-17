package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.key.SellerPK;
import org.springframework.data.jpa.repository.JpaRepository;

import luis122448.SmartShell.application.domain.persistence.entity.SellerEntity;

import java.util.List;

public interface SellerRepository extends JpaRepository<SellerEntity, SellerPK>{
    List<SellerEntity> findByIdcompany(Integer idcompany);
}
