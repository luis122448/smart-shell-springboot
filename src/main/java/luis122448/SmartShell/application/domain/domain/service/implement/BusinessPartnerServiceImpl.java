package luis122448.SmartShell.application.domain.domain.service.implement;

import java.time.LocalDateTime;
import java.util.Optional;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.BusinessPartnerDTO;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BusinessPartnerPK;
import luis122448.SmartShell.application.domain.persistence.mapper.BusinessPartnerMapper;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.BusinessPartnerEntity;
import luis122448.SmartShell.application.domain.persistence.repository.BusinessPartnerRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.BusinessPartnerService;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import static luis122448.SmartShell.util.code.Utils.*;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.*;

@Slf4j
@Service
public class BusinessPartnerServiceImpl implements BusinessPartnerService {
	private final BusinessPartnerRepository businessPartnerRepository;
	private final BusinessPartnerMapper businessPartnerMapper;
	private final SecurityContextInitializer securityContextInitializer;
	public BusinessPartnerServiceImpl(BusinessPartnerRepository businessPartnerRepository, BusinessPartnerMapper businessPartnerMapper, SecurityContextInitializer securityContextInitializer) {
		this.businessPartnerRepository = businessPartnerRepository;
        this.businessPartnerMapper = businessPartnerMapper;
        this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponsePage<BusinessPartnerEntity> findByPage(BusinessPartnerDTO dto, Pageable p)
			throws GenericPageServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		Page<BusinessPartnerEntity> pageEntity = this.businessPartnerRepository.findByPage(idCompany,dto.getTypbuspar(),dto.getCodbuspar(), dto.getBusnam(), dto.getStatus(),p);
		if (pageEntity.isEmpty()){
			throw new GenericPageServiceException(404);
		}
		return new ApiResponsePage<>(Optional.of(pageEntity));
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> findById(BusinessPartnerDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		BusinessPartnerPK id = new BusinessPartnerPK(idCompany,dto.getCodbuspar());
		Optional<BusinessPartnerEntity> searchEntity = this.businessPartnerRepository.findById(id);
		if (searchEntity.isEmpty()){
			throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
		}
		return new ApiResponseObject<>(searchEntity);
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> save(BusinessPartnerDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		String codUser = securityContextInitializer.getCodUser();
		BusinessPartnerPK id = new BusinessPartnerPK(idCompany,dto.getCodbuspar());
		if (this.businessPartnerRepository.existsById(id)){
			throw new GenericObjectServiceException(ID_EXISTS(id.toString()));
		}
		BusinessPartnerEntity newEntity = this.businessPartnerMapper.toBusinessPartnerEntity(dto);
		newEntity.setIdcompany(idCompany);
		newEntity.setCreateby(codUser);
		newEntity.setCreateat(LocalDateTime.now());
		BusinessPartnerEntity saveEntity = this.businessPartnerRepository.save(newEntity);
		return new ApiResponseObject<>(Optional.of(saveEntity));
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> update(BusinessPartnerDTO dto)
			throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		String codUser = securityContextInitializer.getCodUser();
		BusinessPartnerPK id = new BusinessPartnerPK(idcompany,dto.getCodbuspar());
		BusinessPartnerEntity existingEntity = this.businessPartnerRepository.findById(id).orElseThrow(
				() -> new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()))
		);
		BusinessPartnerEntity updateEntity = this.businessPartnerMapper.toBusinessPartnerEntity(dto);
		updateEntity.setIdcompany(idcompany);
		updateEntity.setUpdateby(codUser);
		updateEntity.setUpdateat(LocalDateTime.now());
		copyNonNullProperties(existingEntity,updateEntity);
		BusinessPartnerEntity savedEntity = this.businessPartnerRepository.save(updateEntity);
		return new ApiResponseObject<>(Optional.of(savedEntity));
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> delete(BusinessPartnerDTO dto)
			throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		BusinessPartnerPK id = new BusinessPartnerPK(idcompany,dto.getCodbuspar());
		this.businessPartnerRepository.deleteById(id);
		return new ApiResponseObject<>(Optional.empty());
	}

}
