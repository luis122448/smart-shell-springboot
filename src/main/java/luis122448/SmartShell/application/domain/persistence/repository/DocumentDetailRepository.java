package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.DocumentDetailEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.DocumentDetailPK;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoiceDetailModify;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DocumentDetailRepository extends JpaRepository<DocumentDetailEntity, DocumentDetailPK> {

    List<DocumentDetailEntity> findByIdcompanyAndNumint(Integer idcompany, Long numint);

    @Query(value = "CALL PR_CREATE_DOCUMENT_DETAIL(:IN_IDCOMPANY,:IN_CODUSER,:IN_DOCUMENT,:OUT_NUMITE,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> registerDocumentHeader(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                               @Param("IN_CODUSER") String IN_CODUSER,
                                               @Param("IN_DOCUMENT") String IN_DOCUMENT,
                                               @Param("OUT_NUMITE") Long OUT_NUMITE,
                                               @Param("OUT_CODE") Integer OUT_CODE,
                                               @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                               @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

    @Query(value = "SELECT " +
            "dd.numint," +
            "dd.numite," +
            "dd.typinv," +
            "tt.abrevi AS desinv," +
            "dd.codart," +
            "a.descri AS desart," +
            "dd.etiqueta," +
            "dd.quantity," +
            "dd.price," +
            "dd.impafecto," +
            "dd.impinafecto," +
            "dd.impexonerado," +
            "dd.impgratuito," +
            "dd.impigv," +
            "dd.impisc," +
            "dd.imptribadd01," +
            "dd.imptribadd02," +
            "dd.imptribadd03," +
            "dd.imptribadd04," +
            "dd.impdesc01," +
            "dd.impdesc02," +
            "dd.impdesc03," +
            "dd.impdesc04," +
            "dd.implistprice," +
            "dd.impdesctotal," +
            "dd.impsaleprice," +
            "dd.imptribtotal," +
            "dd.imptotal" +
            " FROM TBL_DOCUMENT_DETAIL dd" +
            " LEFT OUTER JOIN TBL_TYPE_INVENTORY tt ON tt.idcompany = dd.idcompany AND tt.typinv = dd.typinv" +
            " LEFT OUTER JOIN TBL_ARTICLE a ON a.idcompany = dd.idcompany AND a.typinv = dd.typinv AND a.codart = dd.codart" +
            " WHERE" +
            " dd.idcompany = :IN_IDCOMPANY" +
            " AND dd.numint = :IN_NUMINT", nativeQuery = true)
    List<DocumentInvoiceDetailModify> searchDocumentInvoiceDetail(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                                           @Param("IN_NUMINT") Long IN_NUMINT) throws GenericListServiceException;

}
