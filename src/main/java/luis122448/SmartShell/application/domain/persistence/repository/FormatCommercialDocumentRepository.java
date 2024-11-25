package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.FormatCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.FormatCommercialDocumentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormatCommercialDocumentRepository extends JpaRepository<FormatCommercialDocumentEntity,FormatCommercialDocumentPK> {

    @Query("SELECT sdc FROM FormatCommercialDocumentEntity sdc WHERE sdc.idcompany = :idcompany AND " +
            "(sdc.status = :status OR :status = '')")
    List<FormatCommercialDocumentEntity> findByIdcompany(@Param("idcompany") Integer idcompany, @Param("status") String status);

    @Query("SELECT sdc FROM FormatCommercialDocumentEntity sdc WHERE sdc.idcompany = :idcompany " +
            "AND sdc.typcomdoc = :typcomdoc " +
            "AND (sdc.status = :status OR :status = '')")
    List<FormatCommercialDocumentEntity> findByIdcompanyAndTypcomdoc(@Param("idcompany") Integer idcompany,@Param("typcomdoc") Integer typcomdoc, @Param("status") String status);

}
