package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentTransactionService;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentTransactionEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.DocumentTransactionPK;
import luis122448.SmartShell.application.domain.persistence.repository.DocumentTransactionRepository;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTransactionServiceImpl implements DocumentTransactionService {
    private final DocumentTransactionRepository documentTransactionRepository;
    private final SecurityContextInitializer securityContextInitializer;
    public DocumentTransactionServiceImpl(DocumentTransactionRepository documentTransactionRepository, SecurityContextInitializer securityContextInitializer) {
        this.documentTransactionRepository = documentTransactionRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<DocumentTransactionEntity> findByNumint(Long numint) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<DocumentTransactionEntity> list = this.documentTransactionRepository.findByIdcompanyAndNumint(idcompany, numint);
        if (list.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(list));
    }
}
