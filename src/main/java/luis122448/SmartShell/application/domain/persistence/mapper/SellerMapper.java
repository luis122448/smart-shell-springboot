package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.SellerDTO;
import luis122448.SmartShell.application.domain.persistence.entity.SellerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SellerMapper {

    @Mappings({})
    SellerEntity toSellerEntity(SellerDTO t);
    List<SellerEntity> toListSellerEntity(List<SellerDTO> t);

}
