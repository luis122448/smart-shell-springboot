package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ReasonCommercialDocumentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReasonCommercialDocumentRepository extends JpaRepository<ReasonCommercialDocumentEntity, ReasonCommercialDocumentPK> {

    @Query(value = "SELECT * FROM TBL_REASON_COMMERCIAL_DOCUMENT WHERE TYPCOMDOC = :TYPCOMDOC AND INGSALCOM = :INGSALCOM",nativeQuery = true)
    List<ReasonCommercialDocumentEntity> findByLike(@Param("TYPCOMDOC") Integer TYPCOMDOC,
                                                    @Param("INGSALCOM") Integer INGSALCOM) throws GenericListServiceException;

}
