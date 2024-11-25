package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.ArticleSpecificationDTO;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleSpecificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleSpecificationMapper {
    @Mappings({})
    ArticleSpecificationEntity toArticleSpecificationEntity(ArticleSpecificationDTO t);
    List<ArticleSpecificationEntity> toListArticleSpecificationEntity(List<ArticleSpecificationDTO> t);
}
