package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.SituationCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.SituationCommercialDocumentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SituationCommercialDocumentRepository extends JpaRepository<SituationCommercialDocumentEntity, SituationCommercialDocumentPK> {

    @Query("SELECT sdc FROM SituationCommercialDocumentEntity sdc WHERE sdc.idcompany = :idcompany " +
            "AND sdc.typcomdoc = :typcomdoc " +
            "AND ( :status = '' OR sdc.status = :status )")
    List<SituationCommercialDocumentEntity> findByIdcompanyAndTypcomdoc(Integer idcompany, Integer typcomdoc, String status);

    @Query("SELECT sdc FROM SituationCommercialDocumentEntity sdc WHERE sdc.idcompany = :idcompany " +
            "AND ( :status = '' OR sdc.status = :status )")
    List<SituationCommercialDocumentEntity> findByIdcompany(Integer idcompany, String status);
}
