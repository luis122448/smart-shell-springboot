package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.ListPriceEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ListPricePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListPriceRepository extends JpaRepository<ListPriceEntity, ListPricePK> {
    List<ListPriceEntity> findByIdcompany(Integer idcompany);
}
