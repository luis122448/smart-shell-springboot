package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.BusinessPartnerDTO;
import luis122448.SmartShell.application.domain.persistence.entity.BusinessPartnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusinessPartnerMapper {

    @Mappings({})
    BusinessPartnerEntity toBusinessPartnerEntity(BusinessPartnerDTO t);
    List<BusinessPartnerEntity> toListBusinessPartnerEntity(List<BusinessPartnerDTO> t);


}
