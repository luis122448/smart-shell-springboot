package luis122448.SmartShell.application.domain.domain.service.generic;

import luis122448.SmartShell.application.domain.persistence.entity.WarehouseEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.WarehousePK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface WarehouseService extends GenericCrudService<WarehouseEntity, WarehousePK> {

    ApiResponseList<WarehouseEntity> findAll(String status) throws GenericListServiceException;
    ApiResponseList<WarehouseEntity> findByTypinv(Integer typinv, String status) throws GenericListServiceException;

}
