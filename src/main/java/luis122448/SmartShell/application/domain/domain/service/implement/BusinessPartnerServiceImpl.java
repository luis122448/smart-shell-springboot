package luis122448.SmartShell.application.domain.domain.service.implement;

import java.util.Optional;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.application.domain.persistence.repository.constants.LIMITConstants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.BusinessPartnerEntity;
import luis122448.SmartShell.application.domain.persistence.repository.BusinessPartnerRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.BusinessPartnerService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import static luis122448.SmartShell.util.code.Utils.*;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.*;

@Slf4j
@Service
public class BusinessPartnerServiceImpl implements BusinessPartnerService {

	private final BusinessPartnerRepository businessPartnerRepository;
	public BusinessPartnerServiceImpl(BusinessPartnerRepository businessPartnerRepository) {
		super();
		this.businessPartnerRepository = businessPartnerRepository;
	}

	@Override
	public ApiResponseList<BusinessPartnerEntity> findAll(BusinessPartnerEntity t) throws GenericListServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ApiResponsePage<BusinessPartnerEntity> findByPage(BusinessPartnerEntity t, Pageable p)
			throws GenericPageServiceException {
		return new ApiResponsePage<BusinessPartnerEntity>(1,"Ok", Optional.ofNullable(this.businessPartnerRepository.findByPage(t.getTypbuspar(),t.getCodbuspar(), t.getBusnam(), t.getStatus(),p)));
	}

	@Override
	public ApiResponseList<BusinessPartnerEntity> findByLike(BusinessPartnerEntity t)
			throws GenericListServiceException {
		Pageable p = PageRequest.of(0, LIMITConstants.LIMIT_STANDARD);
		if (!t.getCodbuspar().isEmpty()) {
			return new ApiResponseList<BusinessPartnerEntity>(1,"Ok",Optional.ofNullable(this.businessPartnerRepository.findByCodbuspar(t.getCodbuspar(),t.getStatus(),p)));
		} else if(!t.getBusnam().isEmpty()) {
			return new ApiResponseList<BusinessPartnerEntity>(1,"Ok",Optional.ofNullable(this.businessPartnerRepository.findByBusnam(t.getBusnam(),t.getStatus(),p)));
		} else {
			return new ApiResponseList<BusinessPartnerEntity>(-2,"No Case",Optional.empty());
		}
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> findById(BusinessPartnerEntity t) throws GenericObjectServiceException {
		Optional<BusinessPartnerEntity> tmp = this.businessPartnerRepository.findById(t.getCodbuspar());
		return new ApiResponseObject<BusinessPartnerEntity>(1,"Ok",tmp);
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> save(BusinessPartnerEntity t) throws GenericObjectServiceException {
		if (this.businessPartnerRepository.existsById(t.getCodbuspar())){
			return new ApiResponseObject<BusinessPartnerEntity>(-2,ID_EXISTS(t.getCodbuspar()),Optional.empty());
		}
		t.setStatus("S");
		return new ApiResponseObject<BusinessPartnerEntity>(1,"Ok",Optional.of(this.businessPartnerRepository.save(t)));
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> update(BusinessPartnerEntity t)
			throws GenericObjectServiceException {
		if (!this.businessPartnerRepository.existsById(t.getCodbuspar())){
			return new ApiResponseObject<BusinessPartnerEntity>(-2,ID_NOT_EXISTS(t.getCodbuspar()),Optional.empty());
		}
		// I get the most recent object from the database
		BusinessPartnerEntity tmp = this.businessPartnerRepository.findById(t.getCodbuspar()).orElseThrow();
		t.setStatus("S");
		// Copy properties from t to tmp
		// The attributes of t that are not null are copied to tmp
		copyNonNullProperties(t, tmp);
		return new ApiResponseObject<BusinessPartnerEntity>(1,"Ok",Optional.of(this.businessPartnerRepository.save(tmp)));
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> delete(BusinessPartnerEntity t)
			throws GenericObjectServiceException {
		if (!this.businessPartnerRepository.existsById(t.getCodbuspar())){
			return new ApiResponseObject<BusinessPartnerEntity>(-2,ID_NOT_EXISTS(t.getCodbuspar()),Optional.empty());
		}
		BusinessPartnerEntity tmp = this.businessPartnerRepository.findById(t.getCodbuspar()).orElseThrow();
		tmp.setStatus("N");
		return new ApiResponseObject<BusinessPartnerEntity>(1,"Ok",Optional.of(this.businessPartnerRepository.save(tmp)));
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> undelete(BusinessPartnerEntity t)
			throws GenericObjectServiceException {
		if (!this.businessPartnerRepository.existsById(t.getCodbuspar())){
			return new ApiResponseObject<BusinessPartnerEntity>(-2,ID_NOT_EXISTS(t.getCodbuspar()),Optional.empty());
		}
		// I get the most recent object from the database
		BusinessPartnerEntity tmp = this.businessPartnerRepository.findById(t.getCodbuspar()).orElseThrow();
		tmp.setStatus("S");
		return new ApiResponseObject<BusinessPartnerEntity>(1,"Ok",Optional.of(this.businessPartnerRepository.save(tmp)));
	}
}
