package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.TypeCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.repository.TypeCommercialDocumentRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.TypeCommercialDocumentService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import java.util.Optional;

@Service
public class TypeCommercialDocumentServiceImp implements TypeCommercialDocumentService {

	private final TypeCommercialDocumentRepository typeCommercialDocumentRepository;
	public TypeCommercialDocumentServiceImp(
			TypeCommercialDocumentRepository typeCommercialDocumentRepository) {
		super();
		this.typeCommercialDocumentRepository = typeCommercialDocumentRepository;
	}

	@Override
	public ApiResponseList<TypeCommercialDocumentEntity> findAll(TypeCommercialDocumentEntity t) throws GenericListServiceException {
		return new ApiResponseList<TypeCommercialDocumentEntity>(1,"Ok",Optional.of(typeCommercialDocumentRepository.findAll()));
	}

	@Override
	public ApiResponseList<TypeCommercialDocumentEntity> findByLike(TypeCommercialDocumentEntity t) throws GenericListServiceException {
		return null;
	}

	@Override
	public ApiResponseObject<TypeCommercialDocumentEntity> findById(TypeCommercialDocumentEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypeCommercialDocumentEntity> save(TypeCommercialDocumentEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypeCommercialDocumentEntity> update(TypeCommercialDocumentEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypeCommercialDocumentEntity> delete(TypeCommercialDocumentEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypeCommercialDocumentEntity> undelete(TypeCommercialDocumentEntity t)
			throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
