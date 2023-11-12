package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.ListPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListPriceRepository extends JpaRepository<ListPriceEntity, Integer> {
}
