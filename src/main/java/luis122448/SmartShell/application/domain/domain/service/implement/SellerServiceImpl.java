package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.SellerDTO;
import luis122448.SmartShell.application.domain.persistence.entity.primary.SellerPK;
import luis122448.SmartShell.application.domain.persistence.mapper.SellerMapper;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.SellerEntity;
import luis122448.SmartShell.application.domain.persistence.repository.SellerRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.SellerService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static luis122448.SmartShell.util.code.Utils.copyNonNullProperties;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_EXISTS;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_NOT_EXISTS;

@Service
public class SellerServiceImpl implements SellerService {
	private final SellerRepository sellerRepository;
	private final SellerMapper sellerMapper;
	private final SecurityContextInitializer securityContextInitializer;

	public SellerServiceImpl(SellerRepository sellerRepository, SellerMapper sellerMapper, SecurityContextInitializer securityContextInitializer) {
		this.sellerRepository = sellerRepository;
        this.sellerMapper = sellerMapper;
        this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseList<SellerEntity> findAll(String status) throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		List<SellerEntity> listEntity = this.sellerRepository.findByIdcompany(idcompany, status);
		if (listEntity.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<>(Optional.of(listEntity));
	}

	@Override
	public ApiResponseObject<SellerEntity> findById(SellerDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		SellerPK id = new SellerPK(idCompany, dto.getCodsel());
		Optional<SellerEntity> searchEntity = this.sellerRepository.findById(id);
		if (searchEntity.isEmpty()) {
			throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
		}
		return new ApiResponseObject<>(searchEntity);
	}

	@Override
	public ApiResponseObject<SellerEntity> save(SellerDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		String codUser = securityContextInitializer.getCodUser();
		SellerPK id = new SellerPK(idCompany, dto.getCodsel());
		if (this.sellerRepository.existsById(id)) {
			throw new GenericObjectServiceException(ID_EXISTS(dto.toString()));
		}
		SellerEntity newEntity = this.sellerMapper.toSellerEntity(dto);
		newEntity.setIdcompany(idCompany);
		newEntity.setCreateby(codUser);
		newEntity.setCreateat(LocalDateTime.now());
		SellerEntity savedEntity = this.sellerRepository.save(newEntity);
		return new ApiResponseObject<>(Optional.of(savedEntity));
	}

	@Override
	public ApiResponseObject<SellerEntity> update(SellerDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		String codUser = securityContextInitializer.getCodUser();
		SellerPK id = new SellerPK(idCompany, dto.getCodsel());
		SellerEntity existingEntity = this.sellerRepository.findById(id).orElseThrow(
				() -> new GenericObjectServiceException(ID_NOT_EXISTS(id.toString())));
		SellerEntity updatedEntity = this.sellerMapper.toSellerEntity(dto);
		updatedEntity.setIdcompany(idCompany);
		updatedEntity.setUpdateby(codUser);
		updatedEntity.setUpdateat(LocalDateTime.now());
		copyNonNullProperties(existingEntity, updatedEntity);
		SellerEntity savedEntity = this.sellerRepository.save(updatedEntity);
		return new ApiResponseObject<>(Optional.of(savedEntity));
	}

	@Override
	public ApiResponseObject<SellerEntity> delete(SellerDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		SellerPK id = new SellerPK(idCompany, dto.getCodsel());
		this.sellerRepository.deleteById(id);
		return new ApiResponseObject<>(Optional.empty());
	}

}
