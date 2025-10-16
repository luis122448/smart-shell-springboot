package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.persistence.entity.CurrencyEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface CurrencyService {
    ApiResponseList<CurrencyEntity> findAll(String status) throws GenericListServiceException;
}
