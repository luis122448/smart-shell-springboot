package luis122448.SmartShell.application.domain.domain.service.implement;

import java.util.Optional;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BusparPaymentConditionPagoPK;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.BusparPaymentConditionEntity;
import luis122448.SmartShell.application.domain.persistence.repository.BusparPaymentConditionRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.BusparPaymentConditionService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_EXISTS;

@Service
public class BusparPaymentConditionServiceImpl implements BusparPaymentConditionService {

	private final BusparPaymentConditionRepository busparPaymentConditionRepository;
	
	public BusparPaymentConditionServiceImpl(BusparPaymentConditionRepository busparPaymentConditionRepository) {
		super();
		this.busparPaymentConditionRepository = busparPaymentConditionRepository;
	}

	@Override
	public ApiResponseList<BusparPaymentConditionEntity> findAll(BusparPaymentConditionEntity t) throws GenericListServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseList<BusparPaymentConditionEntity> findByLike(BusparPaymentConditionEntity t) throws GenericListServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> findById(BusparPaymentConditionEntity t)
			throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> save(BusparPaymentConditionEntity t) throws GenericObjectServiceException {
		BusparPaymentConditionPagoPK tmp = new BusparPaymentConditionPagoPK(t.getCodbuspar(),t.getTyppaycon());
		if (this.busparPaymentConditionRepository.existsById(tmp)){
			return new ApiResponseObject<BusparPaymentConditionEntity>(-2,ID_EXISTS(tmp.toString()),Optional.empty());
		}
		t.setStatus("S");
		return new ApiResponseObject<BusparPaymentConditionEntity>(1, "Ok", Optional.of(this.busparPaymentConditionRepository.save(t)));
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> update(BusparPaymentConditionEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> delete(BusparPaymentConditionEntity t) throws GenericObjectServiceException {
		BusparPaymentConditionPagoPK tmp = new BusparPaymentConditionPagoPK(t.getCodbuspar(),t.getTyppaycon());
		if (!this.busparPaymentConditionRepository.existsById(tmp)){
			return new ApiResponseObject<BusparPaymentConditionEntity>(-2,ID_EXISTS(tmp.toString()),Optional.empty());
		}
		this.busparPaymentConditionRepository.delete(t);
		return new ApiResponseObject<BusparPaymentConditionEntity>(1, "Ok", Optional.empty());
	}

	@Override
	public ApiResponseObject<BusparPaymentConditionEntity> undelete(BusparPaymentConditionEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
