package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.ReasonCommercialDocumentDTO;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ReasonCommercialDocumentPK;
import luis122448.SmartShell.application.domain.persistence.mapper.ReasonCommercialDocumentMapper;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ReasonCommercialDocumentService;
import luis122448.SmartShell.application.domain.persistence.entity.ReasonCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.repository.ReasonCommercialDocumentRepository;
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
public class ReasonCommercialDocumentServiceImpl implements ReasonCommercialDocumentService {
    private final ReasonCommercialDocumentRepository reasonCommercialDocumentRepository;
    private final ReasonCommercialDocumentMapper reasonCommercialDocumentMapper;
    private final SecurityContextInitializer securityContextInitializer;
    public ReasonCommercialDocumentServiceImpl(ReasonCommercialDocumentRepository reasonCommercialDocumentRepository, ReasonCommercialDocumentMapper reasonCommercialDocumentMapper, SecurityContextInitializer securityContextInitializer) {
        this.reasonCommercialDocumentRepository = reasonCommercialDocumentRepository;
        this.reasonCommercialDocumentMapper = reasonCommercialDocumentMapper;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<ReasonCommercialDocumentEntity> findAll(String status) throws GenericListServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        List<ReasonCommercialDocumentEntity> listEntity = this.reasonCommercialDocumentRepository.findByIdcompany(idCompany, status);
        if (listEntity.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(listEntity));
    }

    @Override
    public ApiResponseList<ReasonCommercialDocumentEntity> findByTypcomdocAndInout(Integer typcomdoc, Integer inout, String status) throws GenericListServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        List<ReasonCommercialDocumentEntity> listEntity = this.reasonCommercialDocumentRepository.findByIdcompanyAndTypcomdocAndInout(idCompany, typcomdoc, inout, status);
        if (listEntity.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(listEntity));
    }

    @Override
    public ApiResponseObject<ReasonCommercialDocumentEntity> findById(ReasonCommercialDocumentDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        ReasonCommercialDocumentPK id = new ReasonCommercialDocumentPK(idCompany, dto.getTypcomdoc(), dto.getInout(),dto.reacomdoc);
        Optional<ReasonCommercialDocumentEntity> searchEntity = this.reasonCommercialDocumentRepository.findById(id);
        if (searchEntity.isEmpty()) {
            throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
        }
        return new ApiResponseObject<>(searchEntity);
    }

    @Override
    public ApiResponseObject<ReasonCommercialDocumentEntity> save(ReasonCommercialDocumentDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        String codUser = securityContextInitializer.getCodUser();
        ReasonCommercialDocumentPK id = new ReasonCommercialDocumentPK(idCompany, dto.getTypcomdoc(), dto.getInout(),dto.reacomdoc);
        if (this.reasonCommercialDocumentRepository.existsById(id)) {
            throw new GenericObjectServiceException(ID_EXISTS(id.toString()));
        }
        ReasonCommercialDocumentEntity newEntity = this.reasonCommercialDocumentMapper.toReasonCommercialDocumentEntity(dto);
        newEntity.setIdcompany(idCompany);
        newEntity.setCreateby(codUser);
        newEntity.setCreateat(LocalDateTime.now());
        ReasonCommercialDocumentEntity savedEntity = this.reasonCommercialDocumentRepository.save(newEntity);
        return new ApiResponseObject<>(Optional.of(savedEntity));
    }

    @Override
    public ApiResponseObject<ReasonCommercialDocumentEntity> update(ReasonCommercialDocumentDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        String codUser = securityContextInitializer.getCodUser();
        ReasonCommercialDocumentPK id = new ReasonCommercialDocumentPK(idCompany, dto.getTypcomdoc(), dto.getInout(),dto.reacomdoc);
        ReasonCommercialDocumentEntity existingEntity = this.reasonCommercialDocumentRepository.findById(id).orElseThrow(
                () -> new GenericObjectServiceException(ID_NOT_EXISTS(id.toString())));
        ReasonCommercialDocumentEntity updatedEntity = this.reasonCommercialDocumentMapper.toReasonCommercialDocumentEntity(dto);
        updatedEntity.setIdcompany(idCompany);
        updatedEntity.setUpdateby(codUser);
        updatedEntity.setUpdateat(LocalDateTime.now());
        copyNonNullProperties(existingEntity, updatedEntity);
        ReasonCommercialDocumentEntity savedEntity = this.reasonCommercialDocumentRepository.save(updatedEntity);
        return new ApiResponseObject<>(Optional.of(savedEntity));
    }

    @Override
    public ApiResponseObject<ReasonCommercialDocumentEntity> delete(ReasonCommercialDocumentDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        ReasonCommercialDocumentPK id = new ReasonCommercialDocumentPK(idCompany, dto.getTypcomdoc(), dto.getInout(),dto.getReacomdoc());
        this.reasonCommercialDocumentRepository.deleteById(id);
        return new ApiResponseObject<>(Optional.empty());
    }

}
