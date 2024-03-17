package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.DocumentInvoiceDetailModifyDTO;
import luis122448.SmartShell.application.domain.domain.model.DocumentInvoicePrintDTO;
import luis122448.SmartShell.application.domain.domain.model.DocumentInvoiceSearchDTO;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoiceDetailModify;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoicePrint;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoiceSearch;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentInvoiceMapper {

    public default DocumentInvoicePrintDTO toDocumentInvoicePrintDTO(DocumentInvoicePrint source){
        DocumentInvoicePrintDTO target = new DocumentInvoicePrintDTO();
        target.setTypformat(source.getTypformat());
        target.setTypcomdoc(source.getTypcomdoc());
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
        target.setTypcomdoc(source.getTypcomdoc());
        target.setDestypcomdoc(source.getDestypcomdoc());
        target.setSitcomdoc(source.getSitcomdoc());
        target.setDessitcomdoc(source.getDessitcomdoc());
        target.setRegistdate(source.getRegistdate());
        target.setIngsalcom(source.getIngsalcom());
        target.setDesingsalcom(source.getDesingsalcom());
        target.setReacomdoc(source.getReacomdoc());
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

    public default DocumentInvoiceDetailModifyDTO toDocumentInvoiceDetailModifyDTO(DocumentInvoiceDetailModify source){
        DocumentInvoiceDetailModifyDTO target = new DocumentInvoiceDetailModifyDTO();
        target.setNumint(source.getNumint());
        target.setNumite(source.getNumite());
        target.setTypinv(source.getTypinv());
        target.setDesinv(source.getDesinv());
        target.setCodart(source.getCodart());
        target.setDesart(source.getDesart());
        target.setEtiqueta(source.getEtiqueta());
        target.setQuantity(source.getQuantity());
        target.setPrice(source.getPrice());
        target.setImpafecto(source.getImpafecto());
        target.setImpinafecto(source.getImpinafecto());
        target.setImpexonerado(source.getImpexonerado());
        target.setImpgratuito(source.getImpgratuito());
        target.setImpigv(source.getImpigv());
        target.setImpisc(source.getImpisc());
        target.setImptribadd01(source.getImptribadd01());
        target.setImptribadd02(source.getImptribadd02());
        target.setImptribadd03(source.getImptribadd03());
        target.setImptribadd04(source.getImptribadd04());
        target.setImpdesc01(source.getImpdesc01());
        target.setImpdesc02(source.getImpdesc02());
        target.setImpdesc03(source.getImpdesc03());
        target.setImpdesc04(source.getImpdesc04());
        target.setImplistprice(source.getImplistprice());
        target.setImpdesctotal(source.getImpdesctotal());
        target.setImpsaleprice(source.getImpsaleprice());
        target.setImptribtotal(source.getImptribtotal());
        target.setImptotal(source.getImptotal());
        return target;
    }

    public default List<DocumentInvoiceDetailModifyDTO> toListDocumentInvoiceDetailModifyDTO(List<DocumentInvoiceDetailModify> t){
        return t.stream().map(this::toDocumentInvoiceDetailModifyDTO).toList();
    }
}
