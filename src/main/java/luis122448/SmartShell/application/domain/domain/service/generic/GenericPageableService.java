package luis122448.SmartShell.application.domain.domain.service.generic;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import org.springframework.data.domain.Pageable;

public interface GenericPageableService<T, Q> {
    ApiResponsePage<T> findByPage(Q q, Pageable p) throws GenericPageServiceException;
}
