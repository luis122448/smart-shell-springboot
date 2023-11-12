package luis122448.SmartShell.application.domain.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import luis122448.SmartShell.application.domain.persistence.entity.BusparPaymentConditionEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BusparPaymentConditionPagoPK;

public interface BusparPaymentConditionRepository extends JpaRepository<BusparPaymentConditionEntity, BusparPaymentConditionPagoPK>{

}
