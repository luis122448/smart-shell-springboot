package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.DocumentKardexEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.DocumentKardexPK;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface DocumentKardexRepository extends JpaRepository<DocumentKardexEntity, DocumentKardexPK> {

    @Query(value = "CALL PR_SENT_DOCUMENT_KARDEX(:IN_IDCOMPANY,:IN_CODUSER,:IN_NUMINT,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> sentDocumentKardex(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                                @Param("IN_CODUSER") String IN_CODUSER,
                                                @Param("IN_NUMINT") Long IN_NUMINT,
                                                @Param("OUT_CODE") Integer OUT_CODE,
                                                @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                                @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

}
