package luis122448.SmartShell.application.domain.domain.service.implement;

import java.util.Optional;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BusparPaymentConditionPK;
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
	private final SecurityContextInitializer securityContextInitializer;
	public BusparPaymentConditionServiceImpl(BusparPaymentConditionRepository busparPaymentConditionRepository, SecurityContextInitializer securityContextInitializer) {
		this.busparPaymentConditionRepository = busparPaymentConditionRepository;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> save(BusparPaymentConditionEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		BusparPaymentConditionPK tmp = new BusparPaymentConditionPK(idcompany,t.getCodbuspar(),t.getTyppaycon());
		if (this.busparPaymentConditionRepository.existsById(tmp)){
			throw new GenericObjectServiceException(ID_EXISTS(tmp.toString()));
		}
		t.setStatus("Y");
		return new ApiResponseObject<BusparPaymentConditionEntity>(Optional.of(this.busparPaymentConditionRepository.save(t)));
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> update(BusparPaymentConditionEntity t) throws GenericObjectServiceException {
		throw new GenericObjectServiceException(405);
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> delete(BusparPaymentConditionPK t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		BusparPaymentConditionEntity busparPaymentConditionEntity = this.busparPaymentConditionRepository.findById(t).orElseThrow(
				() -> new GenericObjectServiceException(ID_EXISTS(t.toString())));
		this.busparPaymentConditionRepository.deleteById(t);
		return new ApiResponseObject<BusparPaymentConditionEntity>(Optional.empty());
	}

}
