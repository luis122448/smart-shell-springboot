package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ReasonCommercialDocumentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReasonCommercialDocumentRepository extends JpaRepository<ReasonCommercialDocumentEntity, ReasonCommercialDocumentPK> {
    @Query("SELECT r FROM ReasonCommercialDocumentEntity r WHERE r.idcompany = :idcompany " +
            "AND r.typcomdoc = :typcomdoc AND r.inout = :inout " +
            "AND ( :status = '' OR r.status = :status )")
    List<ReasonCommercialDocumentEntity> findByIdcompanyAndTypcomdocAndInout(Integer idcompany, Integer typcomdoc, Integer inout, String status) throws GenericListServiceException;

    @Query("SELECT r FROM ReasonCommercialDocumentEntity r WHERE r.idcompany = :idcompany " +
            "AND ( :status = '' OR r.status = :status )")
    List<ReasonCommercialDocumentEntity> findByIdcompany(Integer idcompany, String status) throws GenericListServiceException;
}
