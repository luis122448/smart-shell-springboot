package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ReasonCommercialDocumentPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReasonCommercialDocumentRepository extends JpaRepository<ReasonCommercialDocumentEntity, ReasonCommercialDocumentPK> {
    List<ReasonCommercialDocumentEntity> findByIdcompanyAndTypcomdocAndInout(Integer idcompany, Integer typcomdoc, Integer inout) throws GenericListServiceException;
    List<ReasonCommercialDocumentEntity> findByIdcompany(Integer idcompany) throws GenericListServiceException;
}
