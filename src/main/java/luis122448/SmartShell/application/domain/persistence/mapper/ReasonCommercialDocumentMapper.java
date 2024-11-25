package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.ReasonCommercialDocumentDTO;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReasonCommercialDocumentMapper {

    @Mappings({})
    ReasonCommercialDocumentEntity toReasonCommercialDocumentEntity(ReasonCommercialDocumentDTO t);
    List<ReasonCommercialDocumentEntity> toListReasonCommercialDocumentEntity(List<ReasonCommercialDocumentDTO> t);

}
