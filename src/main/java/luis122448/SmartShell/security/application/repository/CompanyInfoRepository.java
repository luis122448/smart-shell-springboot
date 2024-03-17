package luis122448.SmartShell.security.application.repository;

import luis122448.SmartShell.security.application.entity.CompanyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("CompanyInfoSecurityRepository")
public interface CompanyInfoRepository extends JpaRepository<CompanyInfoEntity, Integer> {

    Optional<CompanyInfoEntity> findByCompany(@Param("company") String company);
}
