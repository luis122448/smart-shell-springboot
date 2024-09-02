package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface DocumentKardexService {
    ApiResponseObject<?> sentDocumentKardex(Long numint) throws GenericProcedureException;
}
