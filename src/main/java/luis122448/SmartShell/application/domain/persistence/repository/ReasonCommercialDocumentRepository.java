package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.ReasonCommercialDocumentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReasonCommercialDocumentRepository extends JpaRepository<ReasonCommercialDocumentEntity, ReasonCommercialDocumentPK> {
    List<ReasonCommercialDocumentEntity> findByIdcompanyAndTypcomdocAndIngsalcom(Integer idcompany, Integer typcomdoc, Integer ingsalcom) throws GenericListServiceException;
    List<ReasonCommercialDocumentEntity> findByIdcompany(Integer idcompany) throws GenericListServiceException;
}
