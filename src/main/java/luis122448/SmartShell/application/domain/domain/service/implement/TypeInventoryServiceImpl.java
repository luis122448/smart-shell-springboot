package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.TypeInventoryEntity;
import luis122448.SmartShell.application.domain.persistence.repository.TypeInventoryRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.TypeInventoryService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import java.util.List;
import java.util.Optional;

@Service
public class TypeInventoryServiceImpl implements TypeInventoryService {

	private final TypeInventoryRepository typeInventoryRepository;
	public TypeInventoryServiceImpl(TypeInventoryRepository typeInventoryRepository) {
		super();
		this.typeInventoryRepository = typeInventoryRepository;
	}

	@Override
	public ApiResponseList<TypeInventoryEntity> findAll() throws GenericListServiceException {
		List<TypeInventoryEntity> list = typeInventoryRepository.findAll();
		if (list.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<TypeInventoryEntity>(Optional.of(list));
	}
}
