package luis122448.SmartShell.application.domain.domain.service.generic;

import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface GenericValidations<T, Q> {

    ApiResponseObject<T> isAvailable(Q q) throws GenericObjectServiceException;
}
