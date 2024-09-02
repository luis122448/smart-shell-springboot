package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.primary.DocumentHeaderPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentGenericSearch;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentGenericPrint;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericFunctionException;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DocumentHeaderRepository extends JpaRepository<DocumentHeaderEntity, DocumentHeaderPK> {

    @Query(value = "SELECT * FROM FN_SEARCH_DOCUMENT(:IN_IDCOMPANY,:IN_TYPCOMDOC,:IN_STARTAT,:IN_FINALAT," +
            ":IN_SITCOMDOC,:IN_REACOMDOC,:IN_SERIE,:IN_TYPPAYCOM,:IN_CODBUSCAR);", nativeQuery = true)
    List<DocumentGenericSearch> searchDocumentGeneric(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                                      @Param("IN_TYPCOMDOC") Integer IN_TYPCOMDOC,
                                                      @Param("IN_STARTAT") LocalDate IN_STARTAT,
                                                      @Param("IN_FINALAT") LocalDate IN_FINALAT,
                                                      @Param("IN_SITCOMDOC") String IN_SITCOMDOC,
                                                      @Param("IN_REACOMDOC") String IN_REACOMDOC,
                                                      @Param("IN_SERIE") String IN_SERIE,
                                                      @Param("IN_TYPPAYCOM") Integer IN_TYPPAYCOM,
                                                      @Param("IN_CODBUSCAR") String IN_CODBUSCAR) throws GenericFunctionException;

    @Query(value = "SELECT * FROM FN_SEARCH_DOCUMENT(:IN_IDCOMPANY,:IN_TYPCOMDOC,:IN_STARTAT,:IN_FINALAT," +
            ":IN_SITCOMDOC,:IN_REACOMDOC,:IN_SERIE,:IN_TYPPAYCOM,:IN_CODBUSCAR);", nativeQuery = true)
    Page<DocumentGenericSearch> pageDocumentGeneric(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                                    @Param("IN_TYPCOMDOC") Integer IN_TYPCOMDOC,
                                                    @Param("IN_STARTAT") LocalDate IN_STARTAT,
                                                    @Param("IN_FINALAT") LocalDate IN_FINALAT,
                                                    @Param("IN_SITCOMDOC") String IN_SITCOMDOC,
                                                    @Param("IN_REACOMDOC") String IN_REACOMDOC,
                                                    @Param("IN_SERIE") String IN_SERIE,
                                                    @Param("IN_TYPPAYCOM") Integer IN_TYPPAYCOM,
                                                    @Param("IN_CODBUSCAR") String IN_CODBUSCAR,
                                                    Pageable pageable) throws GenericFunctionException;

    @Query(value = "CALL PR_CREATE_DOCUMENT_HEADER(:IN_IDCOMPANY,:IN_CODUSER,:IN_DOCUMENT,:OUT_NUMINT,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> registerDocumentHeader(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                               @Param("IN_CODUSER") String IN_CODUSER,
                                               @Param("IN_DOCUMENT") String IN_DOCUMENT,
                                               @Param("OUT_NUMINT") Long OUT_NUMINT,
                                               @Param("OUT_CODE") Integer OUT_CODE,
                                               @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                               @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

    @Query(value = "CALL PR_CALCULATE_IMPORT_DOCUMENT(:IN_IDCOMPANY,:IN_CODUSER,:IN_NUMINT,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> calculateImportDocument(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                                @Param("IN_CODUSER") String IN_CODUSER,
                                                @Param("IN_NUMINT") Long IN_NUMINT,
                                                @Param("OUT_CODE") Integer OUT_CODE,
                                                @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                                @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

    @Query(value = "CALL PR_APPROVED_INVOICE(:IN_IDCOMPANY,:IN_CODUSER,:IN_NUMINT,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> approvedImportDocument(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                             @Param("IN_CODUSER") String IN_CODUSER,
                                             @Param("IN_NUMINT") Long IN_NUMINT,
                                             @Param("OUT_CODE") Integer OUT_CODE,
                                             @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                             @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

    @Query(value = "CALL PR_ONACCOUNT_INVOICE(:IN_IDCOMPANY,:IN_CODUSER,:IN_NUMINT,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> onAccountImportDocument(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                               @Param("IN_CODUSER") String IN_CODUSER,
                                               @Param("IN_NUMINT") Long IN_NUMINT,
                                               @Param("OUT_CODE") Integer OUT_CODE,
                                               @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                               @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

    @Query(value = "CALL PR_CANCEL_INVOICE(:IN_IDCOMPANY,:IN_CODUSER,:IN_NUMINT,:IN_COMMEN,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> cancelImportDocument(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                             @Param("IN_CODUSER") String IN_CODUSER,
                                             @Param("IN_NUMINT") Long IN_NUMINT,
                                             @Param("IN_COMMEN") String IN_COMMEN,
                                             @Param("OUT_CODE") Integer OUT_CODE,
                                             @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                             @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

    @Query(value = "CALL PR_DELETE_INVOICE(:IN_IDCOMPANY,:IN_CODUSER,:IN_NUMINT,:IN_COMMEN,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> deleteImportDocument(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                             @Param("IN_CODUSER") String IN_CODUSER,
                                             @Param("IN_NUMINT") Long IN_NUMINT,
                                             @Param("IN_COMMEN") String IN_COMMEN,
                                             @Param("OUT_CODE") Integer OUT_CODE,
                                             @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                             @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

    @Query(value = "SELECT * FROM FN_PRINT_DOCUMENT_INVOCE(:IN_IDCOMPANY,:IN_CODUSER,:IN_NUMINT)", nativeQuery = true)
    List<DocumentGenericPrint> printDocumentGeneric(@Param("IN_IDCOMPANY") Integer IN_IDCOMPANY,
                                                    @Param("IN_CODUSER") String IN_CODUSER,
                                                    @Param("IN_NUMINT") Long IN_NUMINT) throws GenericListServiceException;

    boolean existsByIdcompanyAndRegistdateAndCodcur(Integer idcompany, LocalDate registdate, String codcur);

}
