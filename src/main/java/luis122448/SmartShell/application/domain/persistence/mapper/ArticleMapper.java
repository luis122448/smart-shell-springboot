package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.ArticleDTO;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mappings({})
    ArticleEntity toArticleEntity(ArticleDTO t);
    List<ArticleEntity> toListArticleEntity(List<ArticleDTO> t);

}
