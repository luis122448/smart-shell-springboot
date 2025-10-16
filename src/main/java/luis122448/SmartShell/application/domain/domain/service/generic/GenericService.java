
package luis122448.SmartShell.application.domain.domain.service.generic;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface GenericService<T, Q> {

	ApiResponseObject<T> findById(Q dto) throws GenericObjectServiceException;
	ApiResponseObject<T> save(Q dto) throws GenericObjectServiceException;
	ApiResponseObject<T> update(Q dto) throws GenericObjectServiceException;
	ApiResponseObject<T> delete(Q dto) throws GenericObjectServiceException;

}
