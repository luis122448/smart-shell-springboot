package luis122448.SmartShell.application.domain.persistence.repository.procedure.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import luis122448.SmartShell.application.domain.persistence.view.TypePaymentConditionViewEntity;

public interface TipoCondicionPagoViewRepository extends JpaRepository<TypePaymentConditionViewEntity,String>{

	@Query(value = "SELECT t FROM TypePaymentConditionViewEntity t WHERE t.codbuspar = :codintcom")
    List<TypePaymentConditionViewEntity> findByCodintcom(@Param("codintcom") String codintcom);
}
