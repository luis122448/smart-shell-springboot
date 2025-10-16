package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.ListPriceEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ListPricePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListPriceRepository extends JpaRepository<ListPriceEntity, ListPricePK> {
    @Query("SELECT l FROM ListPriceEntity l WHERE l.idcompany = :idcompany " +
            "AND ( :status = '' OR l.status = :status )")
    List<ListPriceEntity> findByIdcompany(Integer idcompany, String status);
}
