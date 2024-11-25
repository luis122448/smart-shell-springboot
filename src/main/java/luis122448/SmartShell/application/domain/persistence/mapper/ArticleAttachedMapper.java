package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.ArticleAttachedDTO;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleAttachedEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleAttachedMapper {
    @Mappings({})
    ArticleAttachedEntity toArticleAttachedEntity(ArticleAttachedDTO t);
    List<ArticleAttachedEntity> toListArticleAttachedEntity(List<ArticleAttachedDTO> t);
}
