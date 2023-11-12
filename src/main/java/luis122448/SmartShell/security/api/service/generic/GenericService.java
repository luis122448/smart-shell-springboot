package luis122448.SmartShell.security.api.service.generic;

import org.springframework.data.domain.Pageable;

import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;

public interface GenericService<T> {

	ApiResponseList<T> findAll(T t) throws GenericObjectServiceException;
	
	ApiResponseList<T> findByLike(T t) throws GenericObjectServiceException;
	
	ApiResponsePage<T> findByPage(T t, Pageable p) throws GenericObjectServiceException;
	
	ApiResponseObject<T> findById(T t) throws GenericObjectServiceException;
	
	ApiResponseObject<T> save(T t) throws GenericObjectServiceException;
	
	ApiResponseObject<T> update(T t) throws GenericObjectServiceException;
	
	ApiResponseObject<T> delete(T t) throws GenericObjectServiceException;
}
