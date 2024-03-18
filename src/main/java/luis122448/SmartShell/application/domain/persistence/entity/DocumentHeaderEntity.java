package luis122448.SmartShell.application.domain.persistence.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;
import luis122448.SmartShell.application.domain.persistence.entity.auditing.AuditingEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.DocumentHeaderPK;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//@NamedStoredProcedureQueries({
//	@NamedStoredProcedureQuery(
//			name="registerDocumentHeader",
//			procedureName="PR_CREATE_DOCUMENT_HEADER",
//			parameters={
//                    @StoredProcedureParameter(mode = ParameterMode.IN, name= "IN_DOCUMENT", type= String.class ),
//                    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "OUT_NUMINT", type = Long.class),
//                    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "OUT_CODE", type = Integer.class),
//                    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "OUT_MESSAGE", type = String.class),
//                    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "OUT_LOG", type = String.class)
//            }
//	)
//})
//
//@NamedNativeQuery(name = "searchDocumentInvoice",
//        query = "SELECT * FROM FN_SEARCH_DOCUMENT(:IN_TYPCOMDOC,:IN_STARTAT,:IN_FINALAT,:IN_SITCOMDOC,:IN_REACOMDOC,:IN_SERIE,:IN_TYPPAYCOM,:IN_CODBUSCAR)",
//        resultSetMapping = "mappingSearchDocumentInvoice")
//
//@SqlResultSetMapping(name = "mappingSearchDocumentInvoice",
//        classes = @ConstructorResult(targetClass =  DocumentInvoiceSearch.class,
//                columns = { @ColumnResult(name = "NUMINT", type = Long.class),
//                        @ColumnResult(name = "NUMDOC", type = Long.class),
//                        @ColumnResult(name = "SERIE", type = String.class),
//                        @ColumnResult(name = "DESTYPCOMDOC", type = String.class),
//                        @ColumnResult(name = "DESSITCOMDOC", type = String.class),
//                        @ColumnResult(name = "FREGIS", type = LocalDate.class),
//                        @ColumnResult(name = "DESINGSALCOM", type = String.class),
//                        @ColumnResult(name = "DESREACOMDOC", type = String.class),
//                        @ColumnResult(name = "CODBUSPAR", type = String.class),
//                        @ColumnResult(name = "RAZSOC", type = String.class),
//                        @ColumnResult(name = "DIRECC", type = String.class),
//                        @ColumnResult(name = "DESPLAISS", type = String.class),
//                        @ColumnResult(name = "CODMON", type = String.class),
//                        @ColumnResult(name = "DESSEL", type = String.class),
//                        @ColumnResult(name = "DESTYPPAYCON", type = String.class),
//                        @ColumnResult(name = "IMPSUBTOTAL", type = BigDecimal.class),
//                        @ColumnResult(name = "IMPTOTAL", type = BigDecimal.class)}))
//
//@NamedNativeQuery(name = "printDocumentInvoice",
//        query = "SELECT * FROM FN_PRINT_DOCUMENT_INVOCE(:IN_NUMINT)",
//        resultSetMapping = "mappingPrintDocumentInvoice")
//
//@SqlResultSetMapping(name = "mappingPrintDocumentInvoice",
//        classes = @ConstructorResult(targetClass = DocumentInvoicePrint.class,
//                columns = { @ColumnResult(name = "NUMINT", type = Long.class),
//                        @ColumnResult(name = "NUMDOC", type = Long.class),
//                        @ColumnResult(name = "SERIE", type = String.class),
//                        @ColumnResult(name = "DESDOCCOM", type = String.class),
//                        @ColumnResult(name = "DESSITCOM", type = String.class),
//                        @ColumnResult(name = "FREGIS", type = LocalDate.class),
//                        @ColumnResult(name = "DESINGSALCOM", type = String.class),
//                        @ColumnResult(name = "DESMOTCOM", type = String.class),
//                        @ColumnResult(name = "CODBUSPAR", type = String.class),
//                        @ColumnResult(name = "RAZSOC", type = String.class),
//                        @ColumnResult(name = "DESPLAISS", type = String.class),
//                        @ColumnResult(name = "CODMON", type = String.class),
//                        @ColumnResult(name = "DESSEL", type = String.class),
//                        @ColumnResult(name = "DESPAYCON", type = String.class),
//                        @ColumnResult(name = "IMPPREVEN", type = BigDecimal.class),
//                        @ColumnResult(name = "IMPDESCTOTAL", type = BigDecimal.class),
//                        @ColumnResult(name = "IMPSUBTOTAL", type = BigDecimal.class),
//                        @ColumnResult(name = "IMPTRIBTOTAL", type = BigDecimal.class),
//                        @ColumnResult(name = "IMPTOTAL", type = BigDecimal.class),
//                        @ColumnResult(name = "NUMITE", type = Long.class),
//                        @ColumnResult(name = "TYPINV", type = Integer.class),
//                        @ColumnResult(name = "DESTYPINV", type = String.class),
//                        @ColumnResult(name = "CODART", type = String.class),
//                        @ColumnResult(name = "DESART", type = String.class),
//                        @ColumnResult(name = "ETIQUETA", type = Long.class),
//                        @ColumnResult(name = "DESETIQUETA", type = String.class),
//                        @ColumnResult(name = "CANTID", type = BigDecimal.class),
//                        @ColumnResult(name = "PRICE", type = BigDecimal.class),
//                        @ColumnResult(name = "DETPREVEN", type = BigDecimal.class),
//                        @ColumnResult(name = "DETDESCTOTAL", type = BigDecimal.class),
//                        @ColumnResult(name = "DETSUBTOTAL", type = BigDecimal.class),
//                        @ColumnResult(name = "DETTRIBTOTAL", type = BigDecimal.class),
//                        @ColumnResult(name = "DETTOTAL", type = BigDecimal.class)}))

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@IdClass(DocumentHeaderPK.class)
@Table(schema = "smart_shell", name= "TBL_DOCUMENT_HEADER")
@Entity
public class DocumentHeaderEntity extends AuditingEntity {
    @Id
    private Integer idcompany;
	@Id
    private Long numint;
    private String codext;
    private Long typcomdoc;
    private Long sitcomdoc;
    private String serie;
    private Long numdoc;
    private LocalDate registdate;
    private Long codbranch;
    private Long codplaiss;
    private Long ingsalcom;
    private Long reacomdoc;
    private String codcur;
    private BigDecimal exchangerate;
    private String codbuspar;
    private String busnam;
    private String addres;
    private String poscod;
    private String codsel;
    private Long typpaycon;
    private Long incigv;
    private BigDecimal tasigv;
    private BigDecimal impafecto;
    private BigDecimal impinafecto;
    private BigDecimal impexonerado;
    private BigDecimal impgratuito;
    private BigDecimal impigv;
    private BigDecimal impisc;
    private BigDecimal imptribadd01;
    private BigDecimal imptribadd02;
    private BigDecimal imptribadd03;
    private BigDecimal imptribadd04;
    private BigDecimal impdesc01;
    private BigDecimal impdesc02;
    private BigDecimal impdesc03;
    private BigDecimal impdesc04;
    private BigDecimal implistprice;
    private BigDecimal impdesctotal;
    private BigDecimal impsaleprice;
    private BigDecimal imptribtotal;
    private BigDecimal imptotal;
    private String refere;
    private String observ;
    private String commen;
    private byte[] arcpdf;
    private byte[] arccrd;
    private byte[] arcxml;

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(this);
    }

}