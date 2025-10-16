package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.ListPriceArticleDTO;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListPriceArticleMapper {
    @Mappings({})
    ListPriceArticleEntity toListPriceArticleEntity(ListPriceArticleDTO t);
    List<ListPriceArticleEntity> toListListPriceArticleEntity(List<ListPriceArticleDTO> t);
}
