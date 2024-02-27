package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.DocumentInvoicePrintDTO;
import luis122448.SmartShell.application.domain.domain.model.DocumentInvoiceSearchDTO;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoicePrint;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoiceSearch;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.io.ByteArrayInputStream;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentInvoiceMapper {

//    @Mappings({})
//    DocumentInvoiceSearchDTO toDocumentInvoiceSearchDTO(DocumentInvoiceSearch t);
//    List<DocumentInvoiceSearchDTO> toListDocumentInvoiceSearchDTO(List<DocumentInvoiceSearch> t);
//    @InheritInverseConfiguration
//    DocumentInvoiceSearch toDocumentInvoiceSearch(DocumentInvoiceSearchDTO t);
//    List<DocumentInvoiceSearch> toListDocumentInvoiceSearch(List<DocumentInvoiceSearchDTO> t);
//
//    @Mappings({})
//    DocumentInvoicePrintDTO toDocumentInvoicePrintDTO(DocumentInvoicePrint t);
//    List<DocumentInvoicePrintDTO> toListDocumentInvoicePrintDTO(List<DocumentInvoicePrint> t);
//    @InheritInverseConfiguration
//    DocumentInvoicePrint toDocumentInvoicePrint(DocumentInvoicePrintDTO t);
//    List<DocumentInvoicePrint> toListDocumentInvoicePrint(List<DocumentInvoicePrintDTO> t);

    public default DocumentInvoicePrintDTO toDocumentInvoicePrintDTO(DocumentInvoicePrint source){
        DocumentInvoicePrintDTO target = new DocumentInvoicePrintDTO();
        target.setComdestypidedoc(source.getComdestypidedoc());
        target.setComnroidedoc(source.getComnroidedoc());
        target.setComnam(source.getComnam());
        target.setComaddres(source.getComaddres());
        target.setComposcod(source.getComposcod());
        target.setComgloss(new ByteArrayInputStream(source.getComgloss()));
        target.setNumint(source.getNumint());
        target.setNumdoc(source.getNumdoc());
        target.setSerie(source.getSerie());
        target.setDesdoccom(source.getDesdoccom());
        target.setDessitcomdoc(source.getDessitcomdoc());
        target.setRegistdate(source.getRegistDate());
        target.setDesingsalcom(source.getDesingsalcom());
        target.setDesreacomdoc(source.getDesreacomdoc());
        target.setCodbuspar(source.getCodbuspar());
        target.setDestypidedoc(source.getDestypidedoc());
        target.setNroidedoc(source.getNroidedoc());
        target.setBusnam(source.getBusnam());
        target.setAddres(source.getAddres());
        target.setPoscod(source.getPosCod());
        target.setDesplaiss(source.getDesplaiss());
        target.setCodcur(source.getCodcur());
        target.setCursymbol(source.getCursymbol());
        target.setExchangerate(source.getExchangerate());
        target.setDessel(source.getDessel());
        target.setDespaycon(source.getDespaycon());
        target.setRefere(source.getRefere());
        target.setObserv(source.getObserv());
        target.setCommen(source.getCommen());
        target.setImplistprice(source.getImplistprice());
        target.setImpdesctotal(source.getImpdesctotal());
        target.setImpsaleprice(source.getImpsaleprice());
        target.setImptribtotal(source.getImptribtotal());
        target.setImptotal(source.getImptotal());
        target.setImpname(source.getImpname());
        target.setNumite(source.getNumite());
        target.setTypinv(source.getTypinv());
        target.setDestypinv(source.getDestypinv());
        target.setCodart(source.getCodart());
        target.setDesart(source.getDesart());
        target.setEtiqueta(source.getEtiqueta());
        target.setDesetiqueta(source.getDesetiqueta());
        target.setQuantity(source.getQuantity());
        target.setPrice(source.getPrice());
        target.setDetlistprice(source.getDetlistprice());
        target.setDetdesctotal(source.getDetdesctotal());
        target.setDetsaleprice(source.getDetsaleprice());
        target.setDettribtotal(source.getDettribtotal());
        target.setDettotal(source.getDettotal());
        return target;
    }

    public default List<DocumentInvoicePrintDTO> toListDocumentInvoicePrintDTO(List<DocumentInvoicePrint> t){
        return t.stream().map(this::toDocumentInvoicePrintDTO).toList();
    }

    public default DocumentInvoiceSearchDTO toDocumentInvoiceSearchDTO(DocumentInvoiceSearch source){
        DocumentInvoiceSearchDTO target = new DocumentInvoiceSearchDTO();
        target.setNumint(source.getNumint());
        target.setNumdoc(source.getNumdoc());
        target.setSerie(source.getSerie());
        target.setDestypcomdoc(source.getDestypcomdoc());
        target.setDessitcomdoc(source.getDessitcomdoc());
        target.setRegistdate(source.getRegistdate());
        target.setDesingsalcom(source.getDesingsalcom());
        target.setDesreacomdoc(source.getDesreacomdoc());
        target.setCodbuspar(source.getCodbuspar());
        target.setBusnam(source.getBusnam());
        target.setAddres(source.getAddres());
        target.setDesplaiss(source.getDesplaiss());
        target.setCodcur(source.getCodcur());
        target.setDessel(source.getDessel());
        target.setDestyppaycon(source.getDestyppaycon());
        target.setImpsaleprice(source.getImpsaleprice());
        target.setImptotal(source.getImptotal());
        return target;
    }

    public default List<DocumentInvoiceSearchDTO> toListDocumentInvoiceSearchDTO(List<DocumentInvoiceSearch> t){
        return t.stream().map(this::toDocumentInvoiceSearchDTO).toList();
    }

    public default Page<DocumentInvoiceSearchDTO> toPageDocumentInvoiceSearchDTO(Page<DocumentInvoiceSearch> t){
        return t.map(this::toDocumentInvoiceSearchDTO);
    }
}
