package luis122448.SmartShell.application.domain.domain.service.implement.view;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.view.TypePaymentConditionViewEntity;
import luis122448.SmartShell.application.domain.persistence.repository.procedure.view.TypePaymentConditionViewRepository;
import luis122448.SmartShell.application.domain.domain.service.service.view.TypePaymentConditionViewService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

import java.util.List;
import java.util.Optional;

@Service
public class TypePaymentConditionViewServiceImpl implements TypePaymentConditionViewService {

	private final TypePaymentConditionViewRepository typePaymentConditionViewRepository;
	private final SecurityContextInitializer securityContextInitializer;

	public TypePaymentConditionViewServiceImpl(TypePaymentConditionViewRepository typePaymentConditionViewRepository, SecurityContextInitializer securityContextInitializer) {
		this.typePaymentConditionViewRepository = typePaymentConditionViewRepository;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseList<TypePaymentConditionViewEntity> findByCodBuspar(String codbuspar, String status)
			throws GenericListServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		List<TypePaymentConditionViewEntity> listEntity = this.typePaymentConditionViewRepository.findByCodBuspar(idCompany,codbuspar,status);
		if (listEntity.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<>(Optional.of(listEntity));
	}

}
