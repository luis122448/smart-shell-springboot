package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.FormatCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.FormatCommercialDocumentPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormatCommercialDocumentRepository extends JpaRepository<FormatCommercialDocumentEntity,FormatCommercialDocumentPK> {

    @Query("SELECT sdc FROM FormatCommercialDocumentEntity sdc WHERE sdc.typcomdoc = :typcomdoc AND sdc.status ='Y'")
    List<FormatCommercialDocumentEntity> findByTypcomdoc(Integer typcomdoc);

}
