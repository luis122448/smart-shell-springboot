package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.DocumentHeaderDTO;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentHeaderMapper {

    @Mappings({})
    DocumentHeaderDTO toDocumentHeaderDTO(DocumentHeaderEntity t);
    List<DocumentHeaderDTO> toListDocumentHeaderDTO(List<DocumentHeaderEntity> t);

    @InheritInverseConfiguration
    DocumentHeaderEntity toDocumentHeaderEntity(DocumentHeaderDTO t);
    List<DocumentHeaderEntity> toListDocumentHeaderEntity(List<DocumentHeaderDTO> t);

}
