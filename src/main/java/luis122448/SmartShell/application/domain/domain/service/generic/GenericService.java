
package luis122448.SmartShell.application.domain.domain.service.generic;

import luis122448.SmartShell.util.exception.GenericListServiceException;

import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface GenericService<T> {

	ApiResponseList<T> findByLike(T t) throws GenericListServiceException;
	ApiResponseObject<T> findById(T t) throws GenericObjectServiceException;
	ApiResponseObject<T> save(T t) throws GenericObjectServiceException;
	ApiResponseObject<T> update(T t) throws GenericObjectServiceException;
	ApiResponseObject<T> delete(T t) throws GenericObjectServiceException;
	ApiResponseObject<T> undelete(T t) throws GenericObjectServiceException;

}
