package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoiceSearch;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoicePrint;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericFunctionException;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import org.springframework.data.jpa.repository.JpaRepository;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//@SqlResultSetMapping(
//        name = "DocumentInvoiceSearchMapping",
//        classes = @ConstructorResult(
//                targetClass = DocumentInvoiceSearch.class,
//                columns = {
//                        @ColumnResult(name = "numint", type = Long.class),
//                        @ColumnResult(name = "numdoc", type = Long.class),
//                        @ColumnResult(name = "serie", type = String.class),
//                        @ColumnResult(name = "destypcomdoc", type = String.class),
//                        @ColumnResult(name = "dessitcomdoc", type = String.class),
//                        @ColumnResult(name = "fregis", type = LocalDate.class),
//                        @ColumnResult(name = "desingsalcom", type = String.class),
//                        @ColumnResult(name = "desreacomdoc", type = String.class),
//                        @ColumnResult(name = "codbuspar", type = String.class),
//                        @ColumnResult(name = "razsoc", type = String.class),
//                        @ColumnResult(name = "direcc", type = String.class),
//                        @ColumnResult(name = "desplaiss", type = String.class),
//                        @ColumnResult(name = "codmon", type = String.class),
//                        @ColumnResult(name = "dessel", type = String.class),
//                        @ColumnResult(name = "destyppaycon", type = String.class),
//                        @ColumnResult(name = "impsubtotal", type = BigDecimal.class),
//                        @ColumnResult(name = "imptotal", type = BigDecimal.class)
//                }
//        )
//)
public interface DocumentHeaderRepository extends JpaRepository<DocumentHeaderEntity, Long> {

     @Query(value = "SELECT * FROM FN_SEARCH_DOCUMENT(:IN_TYPCOMDOC,:IN_STARTAT,:IN_FINALAT," +
            ":IN_SITCOMDOC,:IN_REACOMDOC,:IN_SERIE,:IN_TYPPAYCOM,:IN_CODBUSCAR);", nativeQuery = true)
    List<DocumentInvoiceSearch> searchDocumentInvoice(@Param("IN_TYPCOMDOC") Integer IN_TYPCOMDOC,
                                                      @Param("IN_STARTAT") LocalDate IN_STARTAT,
                                                      @Param("IN_FINALAT") LocalDate IN_FINALAT,
                                                      @Param("IN_SITCOMDOC") String IN_SITCOMDOC,
                                                      @Param("IN_REACOMDOC") String IN_REACOMDOC,
                                                      @Param("IN_SERIE") String IN_SERIE,
                                                      @Param("IN_TYPPAYCOM") Integer IN_TYPPAYCOM,
                                                      @Param("IN_CODBUSCAR") String IN_CODBUSCAR) throws GenericFunctionException;

//    @Query(name = "searchDocumentInvoice",nativeQuery = true)
//    List<DocumentInvoiceSearch> searchDocumentInvoice(@Param("IN_TYPCOMDOC") Integer IN_TYPCOMDOC,
//                                                    @Param("IN_STARTAT") LocalDate IN_STARTAT,
//                                                    @Param("IN_FINALAT") LocalDate IN_FINALAT,
//                                                    @Param("IN_SITCOMDOC") String IN_SITCOMDOC,
//                                                    @Param("IN_REACOMDOC") String IN_REACOMDOC,
//                                                    @Param("IN_SERIE") String IN_SERIE,
//                                                    @Param("IN_TYPPAYCOM") Integer IN_TYPPAYCOM,
//                                                    @Param("IN_CODBUSCAR") String IN_CODBUSCAR) throws GenericFunctionException;

//    @Procedure(procedureName = "PR_CREATE_DOCUMENT_HEADER")
//    void registerDocumentHeader(@Param("IN_DOCUMENT") String IN_DOCUMENT,
//                                                @Param("OUT_RESCODE") Integer OUT_RESCODE,
//                                                @Param("OUT_RESMENS") String OUT_RESMENS,
//                                                @Param("OUT_RESLOGS") String OUT_RESLOGS) throws GenericProcedureException;

    @Query(value = "CALL PR_CREATE_DOCUMENT_HEADER(:IN_DOCUMENT,:OUT_NUMINT,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> registerDocumentHeader(@Param("IN_DOCUMENT") String IN_DOCUMENT,
                                       @Param("OUT_NUMINT") Long OUT_NUMINT,
                                       @Param("OUT_CODE") Integer OUT_CODE,
                                       @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                       @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

    @Query(value = "CALL PR_CALCULATE_IMPORT_DOCUMENT(:IN_NUMINT,:OUT_CODE,:OUT_MESSAGE,:OUT_LOG);", nativeQuery = true)
    Map<String, Object> calculateImportDocument(@Param("IN_NUMINT") Long IN_NUMINT,
                                                @Param("OUT_CODE") Integer OUT_CODE,
                                                @Param("OUT_MESSAGE") String OUT_MESSAGE,
                                                @Param("OUT_LOG") String OUT_LOG) throws GenericProcedureException;

    @Query(value = "SELECT * FROM FN_PRINT_DOCUMENT_INVOCE(:IN_NUMINT)", nativeQuery = true)
    List<DocumentInvoicePrint> printDocumentInvoice(@Param("IN_NUMINT") Long IN_NUMINT) throws GenericListServiceException;

//    @Query(name = "printDocumentInvoice", nativeQuery = true)
//    List<DocumentInvoicePrint> printDocumentInvoice(@Param("IN_NUMINT") Long IN_NUMINT) throws GenericListServiceException;

}
