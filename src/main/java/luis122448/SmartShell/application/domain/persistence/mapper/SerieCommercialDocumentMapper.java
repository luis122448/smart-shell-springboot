package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.SerieCommercialDocumentDTO;
import luis122448.SmartShell.application.domain.persistence.entity.SerieCommercialDocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SerieCommercialDocumentMapper {
    @Mappings({})
    SerieCommercialDocumentEntity toSerieCommercialDocumentEntity(SerieCommercialDocumentDTO t);
    List<SerieCommercialDocumentEntity> toListSerieCommercialDocumentEntity(List<SerieCommercialDocumentDTO> t);
}
