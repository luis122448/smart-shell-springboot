package luis122448.SmartShell.application.domain.domain.service.generic;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface GenericCrudService<T,Q> {
    ApiResponseObject<T> save(T t) throws GenericObjectServiceException;
    ApiResponseObject<T> update(T t) throws GenericObjectServiceException;
    ApiResponseObject<T> delete(Q q) throws GenericObjectServiceException;
}
