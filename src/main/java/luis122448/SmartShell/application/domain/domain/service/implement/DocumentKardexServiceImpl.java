package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentKardexService;
import luis122448.SmartShell.application.domain.persistence.repository.DocumentKardexRepository;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class DocumentKardexServiceImpl implements DocumentKardexService {
    private final DocumentKardexRepository documentKardexRepository;
    private final SecurityContextInitializer securityContextInitializer;

    public DocumentKardexServiceImpl(DocumentKardexRepository documentKardexRepository, SecurityContextInitializer securityContextInitializer) {
        this.documentKardexRepository = documentKardexRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseObject<?> sentDocumentKardex(Long numint) throws GenericProcedureException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        String coduser = securityContextInitializer.getCodUser();
        Map<String, Object> obj = this.documentKardexRepository.sentDocumentKardex(idcompany, coduser, numint, 0, "", "");
        if ( (Integer) obj.get("out_code") < 0 ){
            throw new GenericProcedureException(obj.get("out_message").toString(),obj.get("out_log").toString());
        }
        return new ApiResponseObject<>(Optional.empty());
    }

}
