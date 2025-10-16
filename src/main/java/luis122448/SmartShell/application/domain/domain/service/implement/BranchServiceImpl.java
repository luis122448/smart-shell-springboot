package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.BranchDTO;
import luis122448.SmartShell.application.domain.domain.service.service.BranchService;
import luis122448.SmartShell.application.domain.persistence.entity.BranchEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BranchPK;
import luis122448.SmartShell.application.domain.persistence.mapper.BranchMapper;
import luis122448.SmartShell.application.domain.persistence.repository.BranchRepository;
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
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final SecurityContextInitializer securityContextInitializer;

    public BranchServiceImpl(BranchRepository branchRepository, BranchMapper branchMapper, SecurityContextInitializer securityContextInitializer) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<BranchEntity> findAll(String status) throws GenericListServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        List<BranchEntity> listEntity = branchRepository.findByIdcompany(idCompany, status);
        if (listEntity.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(listEntity));
    }

    @Override
    public ApiResponseObject<BranchEntity> findById(BranchDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        BranchPK id = new BranchPK(idCompany, dto.getCodbranch());
        Optional<BranchEntity> searchEntity = branchRepository.findById(id);
        if (searchEntity.isEmpty()) {
            throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
        }
        return new ApiResponseObject<>(searchEntity);
    }

    @Override
    public ApiResponseObject<BranchEntity> save(BranchDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        String codUser = securityContextInitializer.getCodUser();
        BranchPK id = new BranchPK(idCompany, dto.getCodbranch());
        if (branchRepository.existsById(id)) {
            throw new GenericObjectServiceException(ID_EXISTS(id.toString()));
        }
        BranchEntity newEntity = this.branchMapper.toBranchEntity(dto);
        newEntity.setIdcompany(idCompany);
        newEntity.setCreateby(codUser);
        newEntity.setCreateat(LocalDateTime.now());
        BranchEntity savedEntity = branchRepository.save(newEntity);
        return new ApiResponseObject<>(Optional.of(savedEntity));
    }

    @Override
    public ApiResponseObject<BranchEntity> update(BranchDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        String codUser = securityContextInitializer.getCodUser();
        BranchPK id = new BranchPK(idCompany, dto.getCodbranch());
        BranchEntity existingEntity = branchRepository.findById(id).orElseThrow(
                () -> new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()))
        );
        BranchEntity updatedEntity = this.branchMapper.toBranchEntity(dto);
        updatedEntity.setIdcompany(idCompany);
        updatedEntity.setUpdateby(codUser);
        updatedEntity.setUpdateat(LocalDateTime.now());
        copyNonNullProperties(existingEntity, updatedEntity);
        BranchEntity savedEntity = branchRepository.save(updatedEntity);
        return new ApiResponseObject<>(Optional.of(savedEntity));
    }

    @Override
    public ApiResponseObject<BranchEntity> delete(BranchDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        BranchPK id = new BranchPK(idCompany, dto.getCodbranch());
        this.branchRepository.deleteById(id);
        return new ApiResponseObject<>(Optional.empty());
    }
}
