package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.SituationCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.SituationCommercialDocumentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SituationCommercialDocumentRepository extends JpaRepository<SituationCommercialDocumentEntity, SituationCommercialDocumentPK> {

    List<SituationCommercialDocumentEntity> findByIdcompanyAndTypcomdoc(Integer idcompany, Integer typcomdoc);
    List<SituationCommercialDocumentEntity> findByIdcompany(Integer idcompany);
}
