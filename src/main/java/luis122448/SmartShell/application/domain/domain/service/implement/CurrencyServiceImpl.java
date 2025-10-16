package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.service.service.CurrencyService;
import luis122448.SmartShell.application.domain.persistence.entity.CurrencyEntity;
import luis122448.SmartShell.application.domain.persistence.repository.CurrencyRepository;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final SecurityContextInitializer securityContextInitializer;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, SecurityContextInitializer securityContextInitializer) {
        this.currencyRepository = currencyRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<CurrencyEntity> findAll(String status) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<CurrencyEntity> list = currencyRepository.findByAll(idcompany, status);
        if (list.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(list));
    }
}
