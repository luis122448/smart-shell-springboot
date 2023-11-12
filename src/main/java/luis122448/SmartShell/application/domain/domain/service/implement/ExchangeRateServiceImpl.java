package luis122448.SmartShell.application.domain.domain.service.implement;

import java.time.LocalDate;
import java.util.Optional;

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
	public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
		super();
		this.exchangeRateRepository = exchangeRateRepository;
	}

	@Override
	public ApiResponseList<ExchangeRateEntity> findByLike(LocalDate startAt, LocalDate finalAt, String origen,
														  String destin) throws GenericListServiceException {
		return new ApiResponseList<ExchangeRateEntity>(1,"Ok",Optional.ofNullable(this.exchangeRateRepository.findByLike(startAt, finalAt, origen, destin)));
	}

	@Override
	public ApiResponseList<ExchangeRateEntity> findAll(ExchangeRateEntity t) throws GenericListServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseList<ExchangeRateEntity> findByLike(ExchangeRateEntity t) throws GenericListServiceException {
		return new ApiResponseList<ExchangeRateEntity>(1,"Ok",Optional.ofNullable(exchangeRateRepository.findByRegistdate(t.getRegistdate())));
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> findById(ExchangeRateEntity t) throws GenericObjectServiceException {
		ExchangeRatePK tmp =  new ExchangeRatePK(t.getRegistdate(),t.getOrigen(),t.getDestin());
		return new ApiResponseObject<ExchangeRateEntity>(1,"Ok", exchangeRateRepository.findById(tmp));
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> save(ExchangeRateEntity t) throws GenericObjectServiceException {
		ExchangeRatePK tmp =  new ExchangeRatePK(t.getRegistdate(),t.getOrigen(),t.getDestin());
		if (this.exchangeRateRepository.existsById(tmp)){
			return new ApiResponseObject<ExchangeRateEntity>(1,ID_EXISTS(tmp.toString()),Optional.empty());
		}
		t.setStatus("S");
		return new ApiResponseObject<ExchangeRateEntity>(1,"Ok",Optional.of(this.exchangeRateRepository.save(t)));
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> update(ExchangeRateEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> delete(ExchangeRateEntity t) throws GenericObjectServiceException {
		ExchangeRatePK tmp =  new ExchangeRatePK(t.getRegistdate(),t.getOrigen(),t.getDestin());
		if (!this.exchangeRateRepository.existsById(tmp)){
			return new ApiResponseObject<ExchangeRateEntity>(1,ID_NOT_EXISTS(tmp.toString()),Optional.empty());
		}
		this.exchangeRateRepository.delete(t);
		return new ApiResponseObject<ExchangeRateEntity>(1,"Ok",Optional.empty());
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> undelete(ExchangeRateEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
