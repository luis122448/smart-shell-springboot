package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.DocumentDetailDTO;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentDetailEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentDetailMapper {

    @Mappings({})
    DocumentDetailDTO toDocumentDetailDTO(DocumentDetailEntity t);
    List<DocumentDetailDTO> toListDocumentDetailDTO(List<DocumentDetailEntity> t);

    @InheritInverseConfiguration
    DocumentDetailEntity toDocumentDetailEntity(DocumentDetailDTO t);
    List<DocumentDetailEntity> toListDocumentDetailEntity(List<DocumentDetailDTO> t);

}
