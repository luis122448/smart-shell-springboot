package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.BusparPaymentConditionDTO;
import luis122448.SmartShell.application.domain.persistence.entity.BusparPaymentConditionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusparPaymentConditionMapper {

    @Mappings({})
    BusparPaymentConditionEntity toBusparPaymentConditionEntity(BusparPaymentConditionDTO t);
    List<BusparPaymentConditionEntity> toListBusparPaymentConditionEntity(List<BusparPaymentConditionDTO> t);

}