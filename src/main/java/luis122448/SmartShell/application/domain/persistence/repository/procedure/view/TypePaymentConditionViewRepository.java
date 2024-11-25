package luis122448.SmartShell.application.domain.persistence.repository.procedure.view;

import java.util.List;

import luis122448.SmartShell.application.domain.persistence.entity.primary.BusparPaymentConditionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import luis122448.SmartShell.application.domain.persistence.view.TypePaymentConditionViewEntity;

public interface TypePaymentConditionViewRepository extends JpaRepository<TypePaymentConditionViewEntity, BusparPaymentConditionPK>{
	@Query(value = "SELECT t FROM TypePaymentConditionViewEntity t WHERE t.idcompany = :idcompany " +
            "AND t.codbuspar = :codbuspar " +
            "AND ( :status = '' OR t.status = :status )")
    List<TypePaymentConditionViewEntity> findByCodBuspar(@Param("idcompany") Integer idcompany, @Param("codbuspar") String codbuspar, String status);
}
