package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.ListPriceDTO;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListPriceMapper {
    @Mappings({})
    ListPriceEntity toListPriceEntity(ListPriceDTO t);
    List<ListPriceEntity> toListListPriceEntity(List<ListPriceDTO> t);
}
