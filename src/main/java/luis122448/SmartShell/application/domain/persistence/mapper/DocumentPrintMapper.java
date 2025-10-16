package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.DocumentGenericPrintDTO;
import luis122448.SmartShell.application.domain.domain.model.DocumentKardexPrintDTO;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentGenericPrint;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentKardexPrint;
import org.mapstruct.Mapper;

import java.io.ByteArrayInputStream;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentPrintMapper {

    public default DocumentGenericPrintDTO toDocumentGenericPrintDTO(DocumentGenericPrint source){
        DocumentGenericPrintDTO target = new DocumentGenericPrintDTO();
        target.setFormat(source.getFormat());
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
        target.setDesinout(source.getDesinout());
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

    public default List<DocumentGenericPrintDTO> toListDocumentGenericPrintDTO(List<DocumentGenericPrint> t){
        return t.stream().map(this::toDocumentGenericPrintDTO).toList();
    }

    public default DocumentKardexPrintDTO toDocumentKardexPrintDTO(DocumentKardexPrint source){
        DocumentKardexPrintDTO target = new DocumentKardexPrintDTO();
        target.setFormat(source.getFormat());
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
        target.setDesinout(source.getDesinout());
        target.setDesreacomdoc(source.getDesreacomdoc());
        target.setCodbuspar(source.getCodbuspar());
        target.setDestypidedoc(source.getDestypidedoc());
        target.setNroidedoc(source.getNroidedoc());
        target.setBusnam(source.getBusnam());
        target.setAddres(source.getAddres());
        target.setPoscod(source.getPosCod());
        target.setDesplaiss(source.getDesplaiss());
        target.setDesoriwarehouse(source.getDesoriwarehouse());
        target.setDesdeswarehouse(source.getDesdeswarehouse());
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

    public default List<DocumentKardexPrintDTO> toListDocumentKardexPrintDTO(List<DocumentKardexPrint> t){
        return t.stream().map(this::toDocumentKardexPrintDTO).toList();
    }
}
