package luis122448.SmartShell.application.domain.domain.service.implement;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.SerieCommercialDocumentDTO;
import luis122448.SmartShell.application.domain.persistence.entity.primary.SerieCommercialDocumentPK;
import luis122448.SmartShell.application.domain.persistence.mapper.SerieCommercialDocumentMapper;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.SerieCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.repository.SerieCommercialDocumentRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.SerieCommercialDocumentService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static luis122448.SmartShell.util.code.Utils.copyNonNullProperties;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_EXISTS;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_NOT_EXISTS;

@Slf4j
@Service
public class SerieCommercialDocumentServiceImpl implements SerieCommercialDocumentService {
	private final SerieCommercialDocumentRepository serieCommercialDocumentRepository;
	private final SerieCommercialDocumentMapper serieCommercialDocumentMapper;
	private final SecurityContextInitializer securityContextInitializer;

	public SerieCommercialDocumentServiceImpl(SerieCommercialDocumentRepository serieCommercialDocumentRepository, SerieCommercialDocumentMapper serieCommercialDocumentMapper, SecurityContextInitializer securityContextInitializer) {
		this.serieCommercialDocumentRepository = serieCommercialDocumentRepository;
        this.serieCommercialDocumentMapper = serieCommercialDocumentMapper;
        this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseList<SerieCommercialDocumentEntity> findAll(String status) throws GenericListServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		List<SerieCommercialDocumentEntity> listEntity = this.serieCommercialDocumentRepository.findByIdcompany(idCompany,status);
		if (listEntity.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<>(Optional.of(listEntity));
	}

	@Override
	public ApiResponseList<SerieCommercialDocumentEntity> findByTypcomdoc(Integer typcomdoc, String status) throws GenericListServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		List<SerieCommercialDocumentEntity> listEntity = this.serieCommercialDocumentRepository.findByIdcompanyAndTypcomdoc(idCompany,typcomdoc,status);
		if (listEntity.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<>(Optional.of(listEntity));
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> findById(SerieCommercialDocumentDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		SerieCommercialDocumentPK id = new SerieCommercialDocumentPK(idCompany,dto.getTypcomdoc(),dto.getSerie());
		Optional<SerieCommercialDocumentEntity> searchEntity = this.serieCommercialDocumentRepository.findById(id);
		if (searchEntity.isEmpty()) {
			throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
		}
		return new ApiResponseObject<>(searchEntity);
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> save(SerieCommercialDocumentDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		String codUser = securityContextInitializer.getCodUser();
		SerieCommercialDocumentPK id = new SerieCommercialDocumentPK(idCompany,dto.getTypcomdoc(),dto.getSerie());
		if (this.serieCommercialDocumentRepository.existsById(id)){
			throw new GenericObjectServiceException(ID_EXISTS(id.toString()));
		}
		SerieCommercialDocumentEntity newEntity = this.serieCommercialDocumentMapper.toSerieCommercialDocumentEntity(dto);
		newEntity.setIdcompany(idCompany);
		newEntity.setCreateby(codUser);
		newEntity.setCreateat(LocalDateTime.now());
		SerieCommercialDocumentEntity savedEntity = this.serieCommercialDocumentRepository.save(newEntity);
		return new ApiResponseObject<>(Optional.of(savedEntity));
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> update(SerieCommercialDocumentDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		String codUser = securityContextInitializer.getCodUser();
		SerieCommercialDocumentPK id = new SerieCommercialDocumentPK(idCompany,dto.getTypcomdoc(),dto.getSerie());
		SerieCommercialDocumentEntity existingEntity = this.serieCommercialDocumentRepository.findById(id).orElseThrow(
				() -> new GenericObjectServiceException(ID_NOT_EXISTS(id.toString())
		));
		SerieCommercialDocumentEntity updatedEntity = this.serieCommercialDocumentMapper.toSerieCommercialDocumentEntity(dto);
		updatedEntity.setIdcompany(idCompany);
		updatedEntity.setUpdateby(codUser);
		updatedEntity.setUpdateat(LocalDateTime.now());
		copyNonNullProperties(existingEntity,updatedEntity);
		SerieCommercialDocumentEntity savedEntity = this.serieCommercialDocumentRepository.save(updatedEntity);
		return new ApiResponseObject<>(Optional.of(savedEntity));
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> delete(SerieCommercialDocumentDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		SerieCommercialDocumentPK id = new SerieCommercialDocumentPK(idCompany,dto.getTypcomdoc(),dto.getSerie());
		this.serieCommercialDocumentRepository.deleteById(id);
		return new ApiResponseObject<>(Optional.empty());
	}

}
