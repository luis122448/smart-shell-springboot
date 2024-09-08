package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.persistence.entity.TypeInventoryEntity;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface TypeInventoryService{
    ApiResponseList<TypeInventoryEntity> findAll(String status) throws GenericListServiceException;
}
