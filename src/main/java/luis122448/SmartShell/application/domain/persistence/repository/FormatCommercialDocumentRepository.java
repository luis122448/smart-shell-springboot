package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.FormatCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.FormatCommercialDocumentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormatCommercialDocumentRepository extends JpaRepository<FormatCommercialDocumentEntity,FormatCommercialDocumentPK> {

    List<FormatCommercialDocumentEntity> findByIdcompany(@Param("idcompany") Integer idcompany);

    @Query("SELECT sdc FROM FormatCommercialDocumentEntity sdc WHERE sdc.idcompany = :idcompany AND sdc.typcomdoc = :typcomdoc AND sdc.status ='Y'")
    List<FormatCommercialDocumentEntity> findByIdcompanyAndTypcomdoc(Integer idcompany, Integer typcomdoc);

}
