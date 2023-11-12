package luis122448.SmartShell.application.domain.persistence.repository;

import java.util.List;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import luis122448.SmartShell.application.domain.persistence.entity.TypePaymentConditionEntity;

public interface TypePaymentConditionEntityRepository extends JpaRepository<TypePaymentConditionEntity, Short>{
	
	@Query("SELECT tcp FROM TypePaymentConditionEntity tcp "
			+ "INNER JOIN BusparPaymentConditionEntity ccp ON ccp.codbuspar = :codbuspar AND ccp.typpaycon = tcp.typpaycon "
			+ "WHERE 0=0")
    List<TypePaymentConditionEntity> findByCodbuspar(@Param("codbuspar") String codbuspar) throws GenericListServiceException;

}
