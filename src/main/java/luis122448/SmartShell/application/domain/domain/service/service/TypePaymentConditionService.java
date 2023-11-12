package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.TypePaymentConditionEntity;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface TypePaymentConditionService extends GenericService<TypePaymentConditionEntity>{

	ApiResponseList<TypePaymentConditionEntity> findByCodintcom(String codintcom) throws GenericListServiceException;
}
