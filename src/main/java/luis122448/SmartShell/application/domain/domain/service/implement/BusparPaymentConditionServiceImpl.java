package luis122448.SmartShell.application.domain.domain.service.implement;

import java.time.LocalDateTime;
import java.util.Optional;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.BusparPaymentConditionDTO;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BusparPaymentConditionPK;
import luis122448.SmartShell.application.domain.persistence.mapper.BusparPaymentConditionMapper;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.BusparPaymentConditionEntity;
import luis122448.SmartShell.application.domain.persistence.repository.BusparPaymentConditionRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.BusparPaymentConditionService;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_EXISTS;

@Service
public class BusparPaymentConditionServiceImpl implements BusparPaymentConditionService {
	private final BusparPaymentConditionRepository busparPaymentConditionRepository;
	private final BusparPaymentConditionMapper busparPaymentConditionMapper;
	private final SecurityContextInitializer securityContextInitializer;
	public BusparPaymentConditionServiceImpl(BusparPaymentConditionRepository busparPaymentConditionRepository, BusparPaymentConditionMapper busparPaymentConditionMapper, SecurityContextInitializer securityContextInitializer) {
		this.busparPaymentConditionRepository = busparPaymentConditionRepository;
        this.busparPaymentConditionMapper = busparPaymentConditionMapper;
        this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> save(BusparPaymentConditionDTO dto) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		String codUser = securityContextInitializer.getCodUser();
		BusparPaymentConditionPK id = new BusparPaymentConditionPK(idcompany,dto.getCodbuspar(),dto.getTyppaycon());
		if (this.busparPaymentConditionRepository.existsById(id)){
			throw new GenericObjectServiceException(ID_EXISTS(id.toString()));
		}
		BusparPaymentConditionEntity newEntity = this.busparPaymentConditionMapper.toBusparPaymentConditionEntity(dto);
		newEntity.setIdcompany(idcompany);
		newEntity.setCreateby(codUser);
		newEntity.setCreateat(LocalDateTime.now());
		BusparPaymentConditionEntity savedEntity = this.busparPaymentConditionRepository.save(newEntity);
		return new ApiResponseObject<>(Optional.of(savedEntity));
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> update(BusparPaymentConditionDTO dto) throws GenericObjectServiceException {
		throw new GenericObjectServiceException(405);
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> delete(BusparPaymentConditionDTO dto) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		BusparPaymentConditionPK id = new BusparPaymentConditionPK(idcompany,dto.getCodbuspar(),dto.getTyppaycon());
		this.busparPaymentConditionRepository.deleteById(id);
		return new ApiResponseObject<>(Optional.empty());
	}

}
