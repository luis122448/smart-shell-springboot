package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.TypeInventoryEntity;
import luis122448.SmartShell.application.domain.persistence.repository.TypeInventoryRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.TypeInventoryService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import java.util.Optional;

@Service
public class TypeInventoryServiceImpl implements TypeInventoryService {

	private final TypeInventoryRepository typeInventoryRepository;
	public TypeInventoryServiceImpl(TypeInventoryRepository typeInventoryRepository) {
		super();
		this.typeInventoryRepository = typeInventoryRepository;
	}

	@Override
	public ApiResponseList<TypeInventoryEntity> findAll(TypeInventoryEntity t) throws GenericListServiceException {
		return new ApiResponseList<TypeInventoryEntity>(1,"Ok",Optional.of(this.typeInventoryRepository.findAll()));
	}

	@Override
	public ApiResponseList<TypeInventoryEntity> findByLike(TypeInventoryEntity t) throws GenericListServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypeInventoryEntity> findById(TypeInventoryEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypeInventoryEntity> save(TypeInventoryEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypeInventoryEntity> update(TypeInventoryEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypeInventoryEntity> delete(TypeInventoryEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<TypeInventoryEntity> undelete(TypeInventoryEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
