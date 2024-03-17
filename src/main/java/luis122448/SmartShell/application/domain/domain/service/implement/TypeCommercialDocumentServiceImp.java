package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.TypeCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.repository.TypeCommercialDocumentRepository;
import luis122448.SmartShell.application.domain.domain.service.service.TypeCommercialDocumentService;
import luis122448.SmartShell.util.object.api.ApiResponseList;

import java.util.Optional;

@Service
public class TypeCommercialDocumentServiceImp implements TypeCommercialDocumentService {
	private final TypeCommercialDocumentRepository typeCommercialDocumentRepository;
	private final SecurityContextInitializer securityContextInitializer;
	public TypeCommercialDocumentServiceImp(
			TypeCommercialDocumentRepository typeCommercialDocumentRepository, SecurityContextInitializer securityContextInitializer) {
		this.typeCommercialDocumentRepository = typeCommercialDocumentRepository;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseList<TypeCommercialDocumentEntity> findAll() throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		return new ApiResponseList<>(Optional.of(typeCommercialDocumentRepository.findByIdcompany(idcompany)));
	}

}
