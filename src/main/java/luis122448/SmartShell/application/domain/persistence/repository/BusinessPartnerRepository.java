package luis122448.SmartShell.application.domain.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import luis122448.SmartShell.application.domain.persistence.entity.BusinessPartnerEntity;

public interface BusinessPartnerRepository extends JpaRepository<BusinessPartnerEntity, String>{

    @Query("SELECT ic FROM BusinessPartnerEntity ic WHERE ic.codbuspar = :codbuspar AND ( :status = '' OR ic.status = :status )")
    List<BusinessPartnerEntity> findByCodbuspar(@Param("codbuspar") String codbuspar, @Param("status") String status, Pageable pageable);

    @Query("SELECT ic FROM BusinessPartnerEntity ic WHERE UPPER(ic.busnam) LIKE CONCAT('%', UPPER(:busnam), '%') AND ( :status = '' OR ic.status = :status )")
    List<BusinessPartnerEntity> findByBusnam(@Param("busnam") String busnam, @Param("status") String status, Pageable pageable);
    
    @Query("SELECT ic FROM BusinessPartnerEntity ic WHERE"
    		+ " ic.typbuspar = :typbuspar"
    		+ " AND ( :codbuspar = '' OR ic.codbuspar = :codbuspar )"
    		+ " AND UPPER(ic.busnam) LIKE CONCAT('%', UPPER(:busnam), '%')"
    		+ " AND ( :status = '' OR ic.status = :status )")
    Page<BusinessPartnerEntity> findByPage(@Param("typbuspar") Short typbuspar, @Param("codbuspar") String codbuspar, @Param("busnam") String busnam, @Param("status") String status, Pageable pageable);
}
