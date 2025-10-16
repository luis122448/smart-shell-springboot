package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.WarehouseDTO;
import luis122448.SmartShell.application.domain.persistence.entity.WarehouseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    @Mappings({})
    WarehouseEntity toWarehouseEntity(WarehouseDTO t);
    List<WarehouseEntity> toListWarehouseEntity(List<WarehouseDTO> t);
}
