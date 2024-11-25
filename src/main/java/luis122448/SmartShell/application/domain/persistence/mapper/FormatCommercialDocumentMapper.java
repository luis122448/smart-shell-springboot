package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.FormatCommercialDocumentDTO;
import luis122448.SmartShell.application.domain.persistence.entity.FormatCommercialDocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormatCommercialDocumentMapper {
    @Mappings({})
    FormatCommercialDocumentEntity toFormatCommercialDocumentEntity(FormatCommercialDocumentDTO t);
    List<FormatCommercialDocumentEntity> toFormatCommercialDocumentEntityList(List<FormatCommercialDocumentDTO> t);
}
