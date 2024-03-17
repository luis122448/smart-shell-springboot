package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.TypeInventoryEntity;
import luis122448.SmartShell.application.domain.persistence.repository.TypeInventoryRepository;
import luis122448.SmartShell.application.domain.domain.service.service.TypeInventoryService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

import java.util.List;
import java.util.Optional;

@Service
public class TypeInventoryServiceImpl implements TypeInventoryService {
	private final TypeInventoryRepository typeInventoryRepository;
	private final SecurityContextInitializer securityContextInitializer;
	public TypeInventoryServiceImpl(TypeInventoryRepository typeInventoryRepository, SecurityContextInitializer securityContextInitializer) {
		super();
		this.typeInventoryRepository = typeInventoryRepository;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseList<TypeInventoryEntity> findAll() throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		List<TypeInventoryEntity> list = typeInventoryRepository.findByIdcompany(idcompany);
		if (list.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<>(Optional.of(list));
	}
}
