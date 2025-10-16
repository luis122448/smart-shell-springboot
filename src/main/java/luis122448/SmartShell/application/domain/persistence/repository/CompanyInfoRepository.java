package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.CompanyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("CompanyInfoRepository")
public interface CompanyInfoRepository extends JpaRepository<CompanyInfoEntity, Integer> {
    Optional<CompanyInfoEntity> findByIdcompany(Integer idcompany);
}
