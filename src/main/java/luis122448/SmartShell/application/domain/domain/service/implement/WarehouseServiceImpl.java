package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.WarehouseDTO;
import luis122448.SmartShell.application.domain.domain.service.generic.WarehouseService;
import luis122448.SmartShell.application.domain.persistence.entity.WarehouseEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.WarehousePK;
import luis122448.SmartShell.application.domain.persistence.mapper.WarehouseMapper;
import luis122448.SmartShell.application.domain.persistence.repository.WarehouseRepository;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static luis122448.SmartShell.util.code.Utils.copyNonNullProperties;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_EXISTS;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_NOT_EXISTS;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;
    private final SecurityContextInitializer securityContextInitializer;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper, SecurityContextInitializer securityContextInitializer) {
        super();
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<WarehouseEntity> findAll(String status) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<WarehouseEntity> listEntity = warehouseRepository.findByAll(idcompany, status);
        if (listEntity.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(listEntity));
    }

    @Override
    public ApiResponseList<WarehouseEntity> findByTypinv(Integer typinv, String status) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<WarehouseEntity> listEntity = warehouseRepository.findByTypinv(idcompany,typinv, status);
        if (listEntity.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(listEntity));
    }

    @Override
    public ApiResponseObject<WarehouseEntity> findById(WarehouseDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        WarehousePK id = new WarehousePK(idCompany, dto.getTypinv(), dto.getCodwarehouse());
        WarehouseEntity entity = warehouseRepository.findById(id).orElseThrow(
                () -> new GenericObjectServiceException(ID_EXISTS(dto.toString()))
        );
        return new ApiResponseObject<>(Optional.of(entity));
    }

    @Override
    public ApiResponseObject<WarehouseEntity> save(WarehouseDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        String codUser = securityContextInitializer.getCodUser();
        WarehousePK id = new WarehousePK(idCompany, dto.getTypinv(), dto.getCodwarehouse());
        if (warehouseRepository.existsById(id)) {
            throw new GenericObjectServiceException(ID_EXISTS(dto.toString()));
        }
        WarehouseEntity newEntity = this.warehouseMapper.toWarehouseEntity(dto);
        newEntity.setIdcompany(idCompany);
        newEntity.setCreateby(codUser);
        newEntity.setUpdateby(codUser);
        WarehouseEntity savedEntity = warehouseRepository.save(newEntity);
        return new ApiResponseObject<>(Optional.of(savedEntity));
    }

    @Override
    public ApiResponseObject<WarehouseEntity> update(WarehouseDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        String codUser = securityContextInitializer.getCodUser();
        WarehousePK id = new WarehousePK(idCompany, dto.getTypinv(), dto.getCodwarehouse());
        WarehouseEntity existingEntity = warehouseRepository.findById(id).orElseThrow(
                () -> new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()))
        );
        WarehouseEntity updateEntity = warehouseMapper.toWarehouseEntity(dto);
        updateEntity.setIdcompany(idCompany);
        updateEntity.setUpdateby(codUser);
        updateEntity.setUpdateat(LocalDateTime.now());
        copyNonNullProperties(existingEntity, updateEntity);
        WarehouseEntity savedEntity = warehouseRepository.save(updateEntity);
        return new ApiResponseObject<>(Optional.of(savedEntity));
    }

    @Override
    public ApiResponseObject<WarehouseEntity> delete(WarehouseDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        WarehousePK id = new WarehousePK(idCompany, dto.getTypinv(), dto.getCodwarehouse());
        warehouseRepository.deleteById(id);
        return new ApiResponseObject<>(Optional.empty());
    }

}
