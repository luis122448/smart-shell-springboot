package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.TypePaymentConditionEntity;
import luis122448.SmartShell.application.domain.persistence.repository.TypePaymentConditionEntityRepository;
import luis122448.SmartShell.application.domain.domain.service.service.TypePaymentConditionService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

import java.util.Optional;

@Service
public class TypePaymentConditionServiceImpl implements TypePaymentConditionService {
	private final TypePaymentConditionEntityRepository typePaymentConditionEntityRepository;
	private final SecurityContextInitializer securityContextInitializer;
	public TypePaymentConditionServiceImpl(TypePaymentConditionEntityRepository typePaymentConditionEntityRepository, SecurityContextInitializer securityContextInitializer) {
		super();
		this.typePaymentConditionEntityRepository = typePaymentConditionEntityRepository;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseList<TypePaymentConditionEntity> findAll() throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		return new  ApiResponseList<>(Optional.of(this.typePaymentConditionEntityRepository.findByIdcompany(idcompany)));
	}

	@Override
	public ApiResponseList<TypePaymentConditionEntity> findByCodbuspar(String codbuspar) throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		return new ApiResponseList<>(Optional.of(typePaymentConditionEntityRepository.findByIdcompanyAndCodbuspar(idcompany,codbuspar)));
	}

}
