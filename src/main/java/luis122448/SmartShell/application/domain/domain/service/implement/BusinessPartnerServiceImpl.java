package luis122448.SmartShell.application.domain.domain.service.implement;

import java.util.Optional;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.persistence.entity.primary.BusinessPartnerPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.application.domain.persistence.repository.constants.LIMITConstants;
import org.springframework.data.domain.Page;
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
	private final SecurityContextInitializer securityContextInitializer;
	public BusinessPartnerServiceImpl(BusinessPartnerRepository businessPartnerRepository, SecurityContextInitializer securityContextInitializer) {
		this.businessPartnerRepository = businessPartnerRepository;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponsePage<BusinessPartnerEntity> findByPage(BusinessPartnerEntity t, Pageable p)
			throws GenericPageServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		Page<BusinessPartnerEntity> page = this.businessPartnerRepository.findByPage(idcompany,t.getTypbuspar(),t.getCodbuspar(), t.getBusnam(), t.getStatus(),p);
		if (page.isEmpty()){
			throw new GenericPageServiceException(404);
		}
		return new ApiResponsePage<BusinessPartnerEntity>(Optional.of(page));
	}

	@Override
	public ApiResponseList<BusinessPartnerEntity> findByLike(BusinessPartnerEntity t)
			throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		Pageable p = PageRequest.of(0, LIMITConstants.LIMIT_STANDARD);
		if (!t.getCodbuspar().isEmpty()) {
			return new ApiResponseList<BusinessPartnerEntity>(1,"Ok",Optional.ofNullable(this.businessPartnerRepository.findByCodbuspar(idcompany,t.getCodbuspar(),t.getStatus(),p)));
		} else if(!t.getBusnam().isEmpty()) {
			return new ApiResponseList<BusinessPartnerEntity>(1,"Ok",Optional.ofNullable(this.businessPartnerRepository.findByBusnam(idcompany,t.getBusnam(),t.getStatus(),p)));
		} else {
			return new ApiResponseList<BusinessPartnerEntity>(-2,"No Case",Optional.empty());
		}
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> findById(BusinessPartnerEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		Optional<BusinessPartnerEntity> tmp = this.businessPartnerRepository.findById(new BusinessPartnerPK(idcompany,t.getCodbuspar()));
		return new ApiResponseObject<BusinessPartnerEntity>(tmp);
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> save(BusinessPartnerEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		if (this.businessPartnerRepository.existsById(new BusinessPartnerPK(idcompany,t.getCodbuspar()))){
			throw new GenericObjectServiceException(ID_EXISTS(t.getCodbuspar()));
		}
		t.setStatus("Y");
		return new ApiResponseObject<BusinessPartnerEntity>(Optional.of(this.businessPartnerRepository.save(t)));
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> update(BusinessPartnerEntity t)
			throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		BusinessPartnerEntity businessPartnerEntity = this.businessPartnerRepository.findById(new BusinessPartnerPK(idcompany,t.getCodbuspar())).orElseThrow(
				() -> new GenericObjectServiceException(ID_NOT_EXISTS(t.getCodbuspar()))
		);
		// I get the most recent object from the database
		t.setStatus("Y");
		// Copy properties from t to tmp
		// The attributes of t that are not null are copied to tmp
		copyNonNullProperties(t, businessPartnerEntity);
		return new ApiResponseObject<BusinessPartnerEntity>(Optional.of(this.businessPartnerRepository.save(businessPartnerEntity)));
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> delete(BusinessPartnerEntity t)
			throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		BusinessPartnerEntity tmp = this.businessPartnerRepository.findById(new BusinessPartnerPK(idcompany,t.getCodbuspar())).orElseThrow(
				() -> new GenericObjectServiceException(ID_NOT_EXISTS(t.getCodbuspar()))
		);
		tmp.setStatus("N");
		return new ApiResponseObject<BusinessPartnerEntity>(Optional.of(this.businessPartnerRepository.save(tmp)));
	}

	@Override
	public ApiResponseObject<BusinessPartnerEntity> undelete(BusinessPartnerEntity t)
			throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		// I get the most recent object from the database
		BusinessPartnerEntity tmp = this.businessPartnerRepository.findById(new BusinessPartnerPK(idcompany,t.getCodbuspar())).orElseThrow(
				() -> new GenericObjectServiceException(ID_NOT_EXISTS(t.getCodbuspar()))
		);
		tmp.setStatus("Y");
		return new ApiResponseObject<BusinessPartnerEntity>(Optional.of(this.businessPartnerRepository.save(tmp)));
	}
}
