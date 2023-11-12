package luis122448.SmartShell.application.domain.persistence.repository.procedure.impl;

import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.ResultMessage;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import luis122448.SmartShell.application.domain.persistence.repository.procedure.interfaz.DocumentoCabeceraProcedureRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Optional;

import static luis122448.SmartShell.util.constant.MESSAGEConstants.ERROR_UNKNOWN;

@Slf4j
@Repository
public class DocumentoCabeceraProcedureRepositoryImpl implements DocumentoCabeceraProcedureRepository{
    
	@PersistenceContext
	private EntityManager em;

	@Override
	public ApiResponseObject<DocumentHeaderEntity> save(DocumentHeaderEntity t) throws GenericProcedureException {
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	mapper.registerModule(new JavaTimeModule());
            String documento = mapper.writeValueAsString(t);
    		log.info("IN_DOCUMENT => {}",documento);
    		StoredProcedureQuery query = em.createStoredProcedureQuery("PR_CREATE_DOCUMENT_HEADER", "ResultMessage");
    		query.registerStoredProcedureParameter("IN_DOCUMENT", String.class, ParameterMode.IN);
    		query.setParameter("IN_DOCUMENT", documento);
    		query.execute();
    		ResultMessage result = (ResultMessage) query.getSingleResult();
    		Short resCode = result.getRescode();
    		String resMens = result.getResmens();
    		return new ApiResponseObject<DocumentHeaderEntity>(resCode,resMens,Optional.empty());
		} catch (Exception e) {
			return new ApiResponseObject<DocumentHeaderEntity>(-2,ERROR_UNKNOWN,e.getMessage(),Optional.empty());
		}
	}
}
