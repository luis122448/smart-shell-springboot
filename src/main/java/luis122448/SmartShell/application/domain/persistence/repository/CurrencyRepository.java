package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.CurrencyEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.CurrencyPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, CurrencyPK> {

    @Query("SELECT c FROM CurrencyEntity c WHERE c.idcompany = :idcompany " +
            "AND ( :status = '' OR c.status = :status )")
    List<CurrencyEntity> findByAll(Integer idcompany, String status);

}
