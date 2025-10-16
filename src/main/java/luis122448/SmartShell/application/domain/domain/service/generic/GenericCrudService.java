package luis122448.SmartShell.application.domain.domain.service.generic;

import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface GenericCrudService<T, Q> {
    ApiResponseObject<T> save(Q q) throws GenericObjectServiceException;
    ApiResponseObject<T> update(Q q) throws GenericObjectServiceException;
    ApiResponseObject<T> delete(Q q) throws GenericObjectServiceException;
}
