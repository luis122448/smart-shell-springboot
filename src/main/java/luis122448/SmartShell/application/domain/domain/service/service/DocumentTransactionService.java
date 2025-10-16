package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.persistence.entity.DocumentTransactionEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface DocumentTransactionService {
    ApiResponseList<DocumentTransactionEntity> findByNumint(Long numint) throws GenericListServiceException;
}
