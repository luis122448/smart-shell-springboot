package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.BranchDTO;
import luis122448.SmartShell.application.domain.persistence.entity.BranchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    @Mappings({})
    BranchEntity toBranchEntity(BranchDTO branchDTO);
    List<BranchEntity> toBranchEntityList(List<BranchDTO> branchDTOList);
}
