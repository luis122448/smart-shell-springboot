package luis122448.SmartShell.application.domain.persistence.repository;

import java.util.List;

import luis122448.SmartShell.application.domain.persistence.entity.primary.BusinessPartnerPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import luis122448.SmartShell.application.domain.persistence.entity.BusinessPartnerEntity;

public interface BusinessPartnerRepository extends JpaRepository<BusinessPartnerEntity, BusinessPartnerPK>{

    @Query("SELECT ic FROM BusinessPartnerEntity ic WHERE ic.idcompany = :idcompany AND ic.codbuspar = :codbuspar AND ( :status = '' OR ic.status = :status )")
    List<BusinessPartnerEntity> findByCodbuspar(@Param("idcompany") Integer idcompany, @Param("codbuspar") String codbuspar, @Param("status") String status, Pageable pageable);

    @Query("SELECT ic FROM BusinessPartnerEntity ic WHERE ic.idcompany = :idcompany AND UPPER(ic.busnam) LIKE CONCAT('%', UPPER(:busnam), '%') AND ( :status = '' OR ic.status = :status )")
    List<BusinessPartnerEntity> findByBusnam(@Param("idcompany") Integer idcompany, @Param("busnam") String busnam, @Param("status") String status, Pageable pageable);
    
    @Query("SELECT ic FROM BusinessPartnerEntity ic WHERE"
            + " ic.idcompany = :idcompany"
    		+ " AND ic.typbuspar = :typbuspar"
    		+ " AND UPPER(ic.codbuspar) LIKE CONCAT('%', UPPER(:codbuspar), '%')"
    		+ " AND UPPER(ic.busnam) LIKE CONCAT('%', UPPER(:busnam), '%')"
    		+ " AND ( :status = '' OR ic.status = :status )")
    Page<BusinessPartnerEntity> findByPage(@Param("idcompany") Integer idcompany, @Param("typbuspar") Short typbuspar, @Param("codbuspar") String codbuspar, @Param("busnam") String busnam, @Param("status") String status, Pageable pageable);
}
