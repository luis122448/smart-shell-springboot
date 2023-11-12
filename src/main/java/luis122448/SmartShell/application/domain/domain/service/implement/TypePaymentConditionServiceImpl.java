package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.TypePaymentConditionEntity;
import luis122448.SmartShell.application.domain.persistence.repository.TypePaymentConditionEntityRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.TypePaymentConditionService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import java.util.Optional;

@Service
public class TypePaymentConditionServiceImpl implements TypePaymentConditionService {

	private final TypePaymentConditionEntityRepository typePaymentConditionEntityRepository;
	public TypePaymentConditionServiceImpl(TypePaymentConditionEntityRepository typePaymentConditionEntityRepository) {
		super();
		this.typePaymentConditionEntityRepository = typePaymentConditionEntityRepository;
	}

	@Override
	public ApiResponseList<TypePaymentConditionEntity> findAll(TypePaymentConditionEntity t) throws GenericListServiceException {
		return new  ApiResponseList<TypePaymentConditionEntity>(1,"Ok", Optional.of(this.typePaymentConditionEntityRepository.findAll()));
	}

	@Override
	public ApiResponseList<TypePaymentConditionEntity> findByLike(TypePaymentConditionEntity t) throws GenericListServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypePaymentConditionEntity> findById(TypePaymentConditionEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypePaymentConditionEntity> save(TypePaymentConditionEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypePaymentConditionEntity> update(TypePaymentConditionEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypePaymentConditionEntity> delete(TypePaymentConditionEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseList<TypePaymentConditionEntity> findByCodintcom(String codintcom) throws GenericListServiceException {
		return new ApiResponseList<TypePaymentConditionEntity>(1,"Ok",Optional.ofNullable(typePaymentConditionEntityRepository.findByCodbuspar(codintcom)));
	}

	@Override
	public ApiResponseObject<TypePaymentConditionEntity> undelete(TypePaymentConditionEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
