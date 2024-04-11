package luis122448.SmartShell.application.domain.domain.service.implement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.persistence.repository.DocumentHeaderRepository;
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
	private final DocumentHeaderRepository documentHeaderRepository;
	private final SecurityContextInitializer securityContextInitializer;
	public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository, DocumentHeaderRepository documentHeaderRepository, SecurityContextInitializer securityContextInitializer) {
		super();
		this.exchangeRateRepository = exchangeRateRepository;
		this.documentHeaderRepository = documentHeaderRepository;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseList<ExchangeRateEntity> findByLike(LocalDate startAt, LocalDate finalAt, String origen,
														  String destin) throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		List<ExchangeRateEntity> list = this.exchangeRateRepository.findByLike(idcompany, startAt, finalAt, origen, destin);
		if (list.isEmpty()){
			throw new GenericListServiceException(404,String.format("No exchange rate found for the period from %s to %s",startAt,finalAt));
		}
		return new ApiResponseList<ExchangeRateEntity>(Optional.of(list));
	}

	@Override
	public ApiResponseList<ExchangeRateEntity> findByLike(ExchangeRateEntity exchangeRateEntity) throws GenericListServiceException {
		return null;
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> findById(ExchangeRateEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		ExchangeRatePK tmp =  new ExchangeRatePK(idcompany,t.getRegistdate(),t.getOrigen(),t.getDestin());
		return new ApiResponseObject<ExchangeRateEntity>(exchangeRateRepository.findById(tmp));
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> save(ExchangeRateEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		t.setIdcompany(idcompany);
		ExchangeRatePK tmp =  new ExchangeRatePK(idcompany,t.getRegistdate(),t.getOrigen(),t.getDestin());
		if (this.exchangeRateRepository.existsById(tmp)){
			throw new GenericObjectServiceException(ID_EXISTS(tmp.toString()));
		}
		t.setStatus("Y");
		return new ApiResponseObject<ExchangeRateEntity>(Optional.of(this.exchangeRateRepository.save(t)));
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> update(ExchangeRateEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> delete(ExchangeRateEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		ExchangeRatePK exchangeRatePK =  new ExchangeRatePK(idcompany,t.getRegistdate(),t.getOrigen(),t.getDestin());
		if (!this.exchangeRateRepository.existsById(exchangeRatePK)){
			throw new GenericObjectServiceException(ID_NOT_EXISTS(exchangeRatePK.toString()));
		}
		if(this.documentHeaderRepository.existsByIdcompanyAndRegistdateAndCodcur(idcompany,t.getRegistdate(),t.getOrigen()) ||
				this.documentHeaderRepository.existsByIdcompanyAndRegistdateAndCodcur(idcompany,t.getRegistdate(),t.getDestin())){
			throw new GenericObjectServiceException("The exchange rate cannot be deleted, as there are documents registered with the selected exchange rate!");
		}
		this.exchangeRateRepository.deleteById(exchangeRatePK);
		return new ApiResponseObject<ExchangeRateEntity>(Optional.empty());
	}

	@Override
	public ApiResponseObject<ExchangeRateEntity> undelete(ExchangeRateEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
