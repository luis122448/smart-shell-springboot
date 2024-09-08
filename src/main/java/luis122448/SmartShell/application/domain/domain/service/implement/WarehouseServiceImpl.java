package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.service.generic.WarehouseService;
import luis122448.SmartShell.application.domain.persistence.entity.WarehouseEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.WarehousePK;
import luis122448.SmartShell.application.domain.persistence.projection.WarehouseRepository;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final SecurityContextInitializer securityContextInitializer;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, SecurityContextInitializer securityContextInitializer) {
        super();
        this.warehouseRepository = warehouseRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseObject<WarehouseEntity> save(WarehouseEntity warehouseEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<WarehouseEntity> update(WarehouseEntity warehouseEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<WarehouseEntity> delete(WarehousePK warehousePK) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseList<WarehouseEntity> findAll(String status) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<WarehouseEntity> list = warehouseRepository.findByAll(idcompany, status);
        if (list.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(list));
    }

    @Override
    public ApiResponseList<WarehouseEntity> findByTypinv(Integer typinv, String status) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<WarehouseEntity> list = warehouseRepository.findByTypinv(idcompany,typinv, status);
        if (list.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(list));
    }

}
