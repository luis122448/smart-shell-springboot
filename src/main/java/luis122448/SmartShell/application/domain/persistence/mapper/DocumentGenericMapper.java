package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.DocumentGenericDetailModifyDTO;
import luis122448.SmartShell.application.domain.domain.model.DocumentGenericSearchDTO;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentGenericDetailModify;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentGenericSearch;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentGenericMapper {

    public default DocumentGenericSearchDTO toDocumentGenericSearchDTO(DocumentGenericSearch source){
        DocumentGenericSearchDTO target = new DocumentGenericSearchDTO();
        target.setNumint(source.getNumint());
        target.setNumdoc(source.getNumdoc());
        target.setSerie(source.getSerie());
        target.setTypcomdoc(source.getTypcomdoc());
        target.setDestypcomdoc(source.getDestypcomdoc());
        target.setSitcomdoc(source.getSitcomdoc());
        target.setDessitcomdoc(source.getDessitcomdoc());
        target.setRegistdate(source.getRegistdate());
        target.setInout(source.getInout());
        target.setDesinout(source.getDesinout());
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

    public default List<DocumentGenericSearchDTO> toListDocumentGenericSearchDTO(List<DocumentGenericSearch> t){
        return t.stream().map(this::toDocumentGenericSearchDTO).toList();
    }

    public default Page<DocumentGenericSearchDTO> toPageDocumentGenericSearchDTO(Page<DocumentGenericSearch> t){
        return t.map(this::toDocumentGenericSearchDTO);
    }

    public default DocumentGenericDetailModifyDTO toDocumentGenericDetailModifyDTO(DocumentGenericDetailModify source){
        DocumentGenericDetailModifyDTO target = new DocumentGenericDetailModifyDTO();
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

    public default List<DocumentGenericDetailModifyDTO> toListDocumentGenericDetailModifyDTO(List<DocumentGenericDetailModify> t){
        return t.stream().map(this::toDocumentGenericDetailModifyDTO).toList();
    }
}
