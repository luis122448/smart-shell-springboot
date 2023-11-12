package luis122448.SmartShell.application.domain.domain.service.generic;

import org.springframework.data.domain.Pageable;

import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponsePage;

public interface GenericViewService<T> {
	ApiResponseList<T> findByLike(T t) throws GenericObjectServiceException;
	
	ApiResponsePage<T> findByPage(T t, Pageable p) throws GenericObjectServiceException;
}
