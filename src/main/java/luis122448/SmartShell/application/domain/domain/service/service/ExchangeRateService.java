package luis122448.SmartShell.application.domain.domain.service.service;

import java.time.LocalDate;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.ExchangeRateEntity;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface ExchangeRateService extends GenericService<ExchangeRateEntity>{

	ApiResponseList<ExchangeRateEntity> findByLike(LocalDate startAt, LocalDate finalAt, String origen, String destin) throws GenericListServiceException;
}
