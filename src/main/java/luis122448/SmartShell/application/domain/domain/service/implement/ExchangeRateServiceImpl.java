package luis122448.SmartShell.application.domain.domain.service.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.ExchangeRateDTO;
import luis122448.SmartShell.application.domain.persistence.mapper.ExchangeRateMapper;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.ExchangeRateEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ExchangeRatePK;
import luis122448.SmartShell.application.domain.persistence.repository.ExchangeRateRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ExchangeRateService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import static luis122448.SmartShell.util.constant.MESSAGEConstants.*;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
	private final ExchangeRateRepository exchangeRateRepository;
	private final ExchangeRateMapper exchangeRateMapper;
	private final SecurityContextInitializer securityContextInitializer;
	public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository, ExchangeRateMapper exchangeRateMapper, SecurityContextInitializer securityContextInitializer) {
		this.exchangeRateRepository = exchangeRateRepository;
		this.exchangeRateMapper = exchangeRateMapper;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseList<ExchangeRateEntity> findByLike(LocalDate startAt, LocalDate finalAt, String origen,
														  String destin) throws GenericListServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		List<ExchangeRateEntity> list = this.exchangeRateRepository.findByLike(idCompany, startAt, finalAt, origen, destin);
		if (list.isEmpty()){
			throw new GenericListServiceException(404,String.format("No exchange rate found for the period from %s to %s",startAt,finalAt));
		}
		return new ApiResponseList<>(Optional.of(list));
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> findById(ExchangeRateDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		ExchangeRatePK id = new ExchangeRatePK(idCompany,dto.getRegistdate(),dto.getOrigen(),dto.getDestin());
		Optional<ExchangeRateEntity> searchEntity = this.exchangeRateRepository.findById(id);
		if (searchEntity.isEmpty()){
			throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
		}
		return new ApiResponseObject<>(searchEntity);
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> save(ExchangeRateDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		String codUser = securityContextInitializer.getCodUser();
		ExchangeRatePK id = new ExchangeRatePK(idCompany,dto.getRegistdate(),dto.getOrigen(),dto.getDestin());
		if (this.exchangeRateRepository.existsById(id)){
			throw new GenericObjectServiceException(ID_EXISTS(id.toString()));
		}
		ExchangeRateEntity newEntity = this.exchangeRateMapper.toExchangeRateEntity(dto);
		newEntity.setIdcompany(idCompany);
		newEntity.setCreateby(codUser);
		newEntity.setCreateat(LocalDateTime.now());
		ExchangeRateEntity savedEntity = this.exchangeRateRepository.save(newEntity);
		return new ApiResponseObject<>(Optional.of(savedEntity));
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> update(ExchangeRateDTO dto) throws GenericObjectServiceException {
		throw new GenericObjectServiceException(405);
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> delete(ExchangeRateDTO dto) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		ExchangeRatePK id =  new ExchangeRatePK(idcompany,dto.getRegistdate(),dto.getOrigen(),dto.getDestin());
		this.exchangeRateRepository.deleteById(id);
		return new ApiResponseObject<>(Optional.empty());
	}

}
