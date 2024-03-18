package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.TypeBusinessPartnerEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.TypeBusinessPartnerPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeBusinessPartnerRepository extends JpaRepository<TypeBusinessPartnerEntity, TypeBusinessPartnerPK>{
    List<TypeBusinessPartnerEntity> findByIdcompany(Integer idcompany);
}
