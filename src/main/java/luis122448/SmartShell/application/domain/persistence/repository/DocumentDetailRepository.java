package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.DocumentDetailEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.DocumentDetailPK;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface DocumentDetailRepository extends JpaRepository<DocumentDetailEntity, DocumentDetailPK> {

    @Query(value = "CALL PR_CREATE_DOCUMENT_DETAIL(:IN_DOCUMENT,:OUT_NUMITE,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> registerDocumentHeader(@Param("IN_DOCUMENT") String IN_DOCUMENT,
                                               @Param("OUT_NUMITE") Long OUT_NUMITE,
                                               @Param("OUT_CODE") Integer OUT_CODE,
                                               @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                               @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

}
