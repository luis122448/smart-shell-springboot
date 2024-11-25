package luis122448.SmartShell.application.domain.domain.service.service.view;

import luis122448.SmartShell.application.domain.persistence.view.TypePaymentConditionViewEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface TypePaymentConditionViewService {
    ApiResponseList<TypePaymentConditionViewEntity> findByCodBuspar(String codbuspar, String status) throws GenericListServiceException;
}
