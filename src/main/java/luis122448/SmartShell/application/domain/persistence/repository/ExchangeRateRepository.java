package luis122448.SmartShell.application.domain.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import luis122448.SmartShell.application.domain.persistence.entity.ExchangeRateEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.ExchangeRatePK;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, ExchangeRatePK>{

	List<ExchangeRateEntity> findByRegistdate(LocalDate registdate) throws GenericListServiceException;

	@Query("SELECT tc FROM ExchangeRateEntity tc WHERE tc.idcompany = :idcompany AND ( tc.registdate BETWEEN :startAt AND :finalAt )"
			+ " AND ( :origen = '' OR tc.origen = :origen )"
			+ " AND ( :destin = '' OR tc.destin = :destin )")
	List<ExchangeRateEntity> findByLike(@Param("idcompany") Integer idcompany,
										@Param("startAt") LocalDate startAt,
										@Param("finalAt") LocalDate finalAt,
										@Param("origen") String origen,
										@Param("destin") String destin) throws GenericListServiceException;
}
