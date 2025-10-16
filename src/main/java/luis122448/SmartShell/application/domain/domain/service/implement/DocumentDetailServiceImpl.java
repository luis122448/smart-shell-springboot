package luis122448.SmartShell.application.domain.domain.service.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentGenericDetailModify;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentDetailService;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentDetailEntity;
import luis122448.SmartShell.application.domain.persistence.repository.DocumentDetailRepository;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DocumentDetailServiceImpl implements DocumentDetailService {
    private final DocumentDetailRepository documentDetailRepository;
    private final SecurityContextInitializer securityContextInitializer;
    public DocumentDetailServiceImpl(DocumentDetailRepository documentDetailRepository, SecurityContextInitializer securityContextInitializer) {
        this.documentDetailRepository = documentDetailRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseObject<DocumentDetailEntity> registerDocumentDetail(DocumentDetailEntity t) throws GenericObjectServiceException {
        try {
            System.out.println("Json: " + t.toJson());
            Integer idcompany = securityContextInitializer.getIdCompany();
            String coduser = securityContextInitializer.getCodUser();
            Map<String, Object> obj = this.documentDetailRepository.registerDocumentDetail(idcompany,coduser,t.toJson(), 0L,0,"","");
			for (String key : obj.keySet()) {
				Object value = obj.get(key);
				System.out.println("Clave: " + key + ", Valor: " + value);
			}
            return new ApiResponseObject<DocumentDetailEntity>((Integer) obj.get("out_code"), obj.get("out_message").toString(), obj.get("out_log").toString(),Optional.empty());
        } catch (GenericProcedureException | JsonProcessingException e) {
            throw new GenericObjectServiceException(e);
        }
    }
    public ApiResponseList<DocumentDetailEntity> findByNumint(Long numint) throws GenericListServiceException{
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<DocumentDetailEntity> detailEntityList = this.documentDetailRepository.findByIdcompanyAndNumint(idcompany,numint);
        if (detailEntityList.isEmpty()){
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(detailEntityList));
    }

    public ApiResponseList<DocumentGenericDetailModify> searchDocumentGenericDetail(Long numint) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<DocumentGenericDetailModify> detailEntityList = this.documentDetailRepository.searchDocumentGenericDetail(idcompany,numint);
        if (detailEntityList.isEmpty()){
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(detailEntityList));
    }

}
