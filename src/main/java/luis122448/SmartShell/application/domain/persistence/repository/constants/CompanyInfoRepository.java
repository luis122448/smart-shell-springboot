package luis122448.SmartShell.application.domain.persistence.repository.constants;

import luis122448.SmartShell.application.domain.persistence.entity.CompanyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfoEntity, Integer> {
    Optional<CompanyInfoEntity> findByIdcompany(Integer idcompany);
}
