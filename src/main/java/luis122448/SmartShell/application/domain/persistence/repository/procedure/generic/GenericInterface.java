package luis122448.SmartShell.application.domain.persistence.repository.procedure.generic;

import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface GenericInterface<T> {
	
	ApiResponseObject<T> save (T t) throws GenericProcedureException;
}
