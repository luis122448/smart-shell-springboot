package luis122448.SmartShell.application.domain.domain.service.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.lettuce.core.ScriptOutputType;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class DocumentHeaderServiceImpl implements DocumentHeaderService {

	private final DocumentHeaderRepository documentHeaderRepository;
	public DocumentHeaderServiceImpl(DocumentHeaderRepository documentHeaderRepository) {
		super();
		this.documentHeaderRepository = documentHeaderRepository;;
	}

	@Override
	public ApiResponseObject<DocumentHeaderEntity> findById(DocumentHeaderEntity t) throws GenericObjectServiceException {
		return new ApiResponseObject<DocumentHeaderEntity>(1,"Ok",documentHeaderRepository.findById(t.getNumint()));
	}

	@Override
	public ApiResponseObject<DocumentHeaderEntity> registerDocumentHeader(DocumentHeaderEntity t) throws GenericObjectServiceException {
		try {
			Map<String, Object> obj = this.documentHeaderRepository.registerDocumentHeader(t.toJson(), 0L,0,"","");
			for (String key : obj.keySet()) {
				Object value = obj.get(key);
				System.out.println("Clave: " + key + ", Valor: " + value);
			}
			if ((Integer) obj.get("out_code") < 0 ){
				throw new GenericObjectServiceException(obj.get("out_message").toString(), obj.get("out_log").toString());
			}
			Optional<DocumentHeaderEntity> tmp = this.documentHeaderRepository.findById((Long) obj.get("out_numint"));
			return new ApiResponseObject<DocumentHeaderEntity>((Integer) obj.get("out_code"), obj.get("out_message").toString(), obj.get("out_log").toString(),tmp);
		} catch (GenericProcedureException | JsonProcessingException e) {
			throw new GenericObjectServiceException(e);
		}
	}

	@Override
	public ApiResponseList<DocumentInvoicePrint> printDocumentInvoice(Long numint) throws GenericListServiceException {
		return new ApiResponseList<DocumentInvoicePrint>(1,"Ok",Optional.of(this.documentHeaderRepository.printDocumentInvoice(numint)));
	}

	@Override
	public ApiResponseList<DocumentInvoiceSearch> searchDocumentInvoice(DocumentInvoiceSearchFilterDTO t) throws GenericListServiceException {
		try {
			return new  ApiResponseList<DocumentInvoiceSearch>(1,"Ok",
					Optional.of(this.documentHeaderRepository.searchDocumentInvoice(t.getTypcomdoc(),t.getStartat(),t.getFinalat(),t.getSitcomdoc(),t.getReacomdoc(),t.getSerie(),t.getTyppaycon(),t.getCodbuspar())));
		} catch (GenericFunctionException e) {
			throw new GenericListServiceException(e);
		}
	}

	@Override
	public ApiResponsePage<DocumentInvoiceSearch> pageDocumentInvoice(DocumentInvoiceSearchFilterDTO t, Pageable pageable) throws GenericPageServiceException {
		try {
			Page<DocumentInvoiceSearch> obj = this.documentHeaderRepository.pageDocumentInvoice(t.getTypcomdoc(),t.getStartat(),t.getFinalat(),t.getSitcomdoc(),t.getReacomdoc(),t.getSerie(),t.getTyppaycon(),t.getCodbuspar(),pageable);
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
		Map<String, Object> obj = this.documentHeaderRepository.calculateImportDocument(numint,0,"","");
		if ( (Integer) obj.get("out_code") < 0 ){
			throw new GenericProcedureException(obj.get("out_message").toString(),obj.get("out_log").toString());
		}
		return new ApiResponseObject<>(1,"Ok",Optional.empty());
	}

	@Override
	public ApiResponseObject<?> cancelImportDocument(Long numint) throws GenericProcedureException {
		Map<String, Object> obj = this.documentHeaderRepository.cancelImportDocument(numint,0,"","");
		if ( (Integer) obj.get("out_code") < 0 ){
			throw new GenericProcedureException(obj.get("out_message").toString(),obj.get("out_log").toString());
		}
		return new ApiResponseObject<>(1,"Ok",Optional.empty());
	}
}
