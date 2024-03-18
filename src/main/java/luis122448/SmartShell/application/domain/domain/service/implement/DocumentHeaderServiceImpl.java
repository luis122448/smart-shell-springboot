package luis122448.SmartShell.application.domain.domain.service.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.persistence.entity.primary.DocumentHeaderPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.application.domain.domain.model.DocumentInvoiceSearchFilterDTO;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentHeaderEntity;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoiceSearch;
import luis122448.SmartShell.application.domain.persistence.projection.DocumentInvoicePrint;
import luis122448.SmartShell.application.domain.persistence.repository.DocumentHeaderRepository;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericFunctionException;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentHeaderService;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import luis122448.SmartShell.util.object.api.ApiResponsePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class DocumentHeaderServiceImpl implements DocumentHeaderService {
	private final DocumentHeaderRepository documentHeaderRepository;
	private final SecurityContextInitializer securityContextInitializer;
	public DocumentHeaderServiceImpl(DocumentHeaderRepository documentHeaderRepository, SecurityContextInitializer securityContextInitializer) {
		this.documentHeaderRepository = documentHeaderRepository;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseObject<DocumentHeaderEntity> findByNumint(Long numint) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		DocumentHeaderEntity documentHeaderEntity = this.documentHeaderRepository.findById(new DocumentHeaderPK(idcompany,numint)).orElseThrow(
				() -> new GenericObjectServiceException(404)
		);
		return new ApiResponseObject<>(Optional.of(documentHeaderEntity));
	}

	@Override
	public ApiResponseObject<DocumentHeaderEntity> registerDocumentHeader(DocumentHeaderEntity t) throws GenericObjectServiceException {
		try {
			Integer idcompany = securityContextInitializer.getIdCompany();
			String coduser = securityContextInitializer.getCodUser();
			Map<String, Object> obj = this.documentHeaderRepository.registerDocumentHeader(idcompany,coduser,t.toJson(), 0L,0,"","");
			for (String key : obj.keySet()) {
				Object value = obj.get(key);
				System.out.println("Clave: " + key + ", Valor: " + value);
			}
			if ((Integer) obj.get("out_code") < 0 ){
				throw new GenericObjectServiceException(obj.get("out_message").toString(), obj.get("out_log").toString());
			}
			Optional<DocumentHeaderEntity> tmp = this.documentHeaderRepository.findById(new DocumentHeaderPK(idcompany,(Long) obj.get("out_numint")));
			return new ApiResponseObject<DocumentHeaderEntity>((Integer) obj.get("out_code"), obj.get("out_message").toString(), obj.get("out_log").toString(),tmp);
		} catch (GenericProcedureException | JsonProcessingException e) {
			throw new GenericObjectServiceException(e);
		}
	}

	@Override
	public ApiResponseList<DocumentInvoicePrint> printDocumentInvoice(Long numint) throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		String coduser = securityContextInitializer.getCodUser();
		List<DocumentInvoicePrint> list = this.documentHeaderRepository.printDocumentInvoice(idcompany,coduser,numint);
		if (list.isEmpty()){
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<>(Optional.of(list));
	}

	@Override
	public ApiResponseList<DocumentInvoiceSearch> searchDocumentInvoice(DocumentInvoiceSearchFilterDTO t) throws GenericListServiceException {
		try {
			Integer idcompany = securityContextInitializer.getIdCompany();
			List<DocumentInvoiceSearch> list = this.documentHeaderRepository.searchDocumentInvoice(idcompany,t.getTypcomdoc(),t.getStartat(),t.getFinalat(),t.getSitcomdoc(),t.getReacomdoc(),t.getSerie(),t.getTyppaycon(),t.getCodbuspar());
			if (list.isEmpty()){
				throw new GenericListServiceException(404);
			}
			return new  ApiResponseList<>(Optional.of(list));
		} catch (GenericFunctionException e) {
			throw new GenericListServiceException(e);
		}
	}

	@Override
	public ApiResponsePage<DocumentInvoiceSearch> pageDocumentInvoice(DocumentInvoiceSearchFilterDTO t, Pageable pageable) throws GenericPageServiceException {
		try {
			Integer idcompany = securityContextInitializer.getIdCompany();
			Page<DocumentInvoiceSearch> obj = this.documentHeaderRepository.pageDocumentInvoice(idcompany,t.getTypcomdoc(),t.getStartat(),t.getFinalat(),t.getSitcomdoc(),t.getReacomdoc(),t.getSerie(),t.getTyppaycon(),t.getCodbuspar(),pageable);
			if (obj.isEmpty()){
				throw new GenericPageServiceException(404);
			}
			return new ApiResponsePage<DocumentInvoiceSearch>(Optional.of(obj));
		} catch (GenericFunctionException e) {
			throw new GenericPageServiceException(e);
		}
	}

	@Override
	public ApiResponseObject<?> calculateImportDocument(Long numint) throws GenericProcedureException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		String coduser = securityContextInitializer.getCodUser();
		Map<String, Object> obj = this.documentHeaderRepository.calculateImportDocument(idcompany,coduser,numint,0,"","");
		if ( (Integer) obj.get("out_code") < 0 ){
			throw new GenericProcedureException(obj.get("out_message").toString(),obj.get("out_log").toString());
		}
		return new ApiResponseObject<>(Optional.empty());
	}

	@Override
	public ApiResponseObject<?> approvedImportDocument(Long numint) throws GenericProcedureException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		String coduser = securityContextInitializer.getCodUser();
		Map<String, Object> obj = this.documentHeaderRepository.approvedImportDocument(idcompany,coduser,numint,0,"","");
		if ( (Integer) obj.get("out_code") < 0 ){
			throw new GenericProcedureException(obj.get("out_message").toString(),obj.get("out_log").toString());
		}
		return new ApiResponseObject<>(Optional.empty());
	}

	@Override
	public ApiResponseObject<?> onAccountImportDocument(Long numint) throws GenericProcedureException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		String coduser = securityContextInitializer.getCodUser();
		Map<String, Object> obj = this.documentHeaderRepository.onAccountImportDocument(idcompany,coduser,numint,0,"","");
		if ( (Integer) obj.get("out_code") < 0 ){
			throw new GenericProcedureException(obj.get("out_message").toString(),obj.get("out_log").toString());
		}
		return new ApiResponseObject<>(Optional.empty());
	}

	@Override
	public ApiResponseObject<?> cancelImportDocument(Long numint, String commen) throws GenericProcedureException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		String coduser = securityContextInitializer.getCodUser();
		Map<String, Object> obj = this.documentHeaderRepository.cancelImportDocument(idcompany,coduser,numint,commen,0,"","");
		if ( (Integer) obj.get("out_code") < 0 ){
			throw new GenericProcedureException(obj.get("out_message").toString(),obj.get("out_log").toString());
		}
		return new ApiResponseObject<>(Optional.empty());
	}

	@Override
	public ApiResponseObject<?> deleteImportDocument(Long numint, String commen) throws GenericProcedureException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		String coduser = securityContextInitializer.getCodUser();
		Map<String, Object> obj = this.documentHeaderRepository.deleteImportDocument(idcompany,coduser,numint,commen,0,"","");
		log.info(obj.get("out_code").toString());
		log.info("Error: {}, {}",obj.get("out_message").toString(),obj.get("out_log").toString());
		if ( (Integer) obj.get("out_code") < 0 ){
			throw new GenericProcedureException(obj.get("out_message").toString(),obj.get("out_log").toString());
		}
		return new ApiResponseObject<>(Optional.empty());
	}
}
