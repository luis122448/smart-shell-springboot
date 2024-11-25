package luis122448.SmartShell.application.domain.persistence.mapper;

import luis122448.SmartShell.application.domain.domain.model.ExchangeRateDTO;
import luis122448.SmartShell.application.domain.persistence.entity.ExchangeRateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {
    @Mappings({})
    ExchangeRateEntity toExchangeRateEntity(ExchangeRateDTO t);
    List<ExchangeRateEntity> toListExchangeRateEntity(List<ExchangeRateDTO> t);
}
