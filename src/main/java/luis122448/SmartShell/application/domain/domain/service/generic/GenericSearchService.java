package luis122448.SmartShell.application.domain.domain.service.generic;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface GenericSearchService<T, Q> {

    ApiResponseList<T> findByLike(T t) throws GenericListServiceException;
    ApiResponseObject<T> findById(Q q) throws GenericObjectServiceException;

}
