package luis122448.SmartShell.application.domain.domain.service.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.DocumentDetailService;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentDetailEntity;
import luis122448.SmartShell.application.domain.persistence.repository.DocumentDetailRepository;
import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class DocumentDetailServiceImpl implements DocumentDetailService {

    private final DocumentDetailRepository documentDetailRepository;
    public DocumentDetailServiceImpl(DocumentDetailRepository documentDetailRepository) {
        this.documentDetailRepository = documentDetailRepository;
    }

//    @Override
//    public ApiResponseObject<DocumentDetailEntity> save(DocumentDetailEntity t) throws GenericObjectServiceException {
//        BigDecimal zero = new BigDecimal("0");
//        BigDecimal igv =  new BigDecimal("0.18");
//        BigDecimal isc = new BigDecimal("0");
//        // Validations
//        if (t.getNumint()<=0){
//            throw new GenericObjectServiceException("The assigned internal number is not valid");
//        }
//        DocumentDetailPK pk = new DocumentDetailPK(t.getNumint(),t.getNumite());
//        if (this.documentDetailRepository.existsById(pk)){
//            throw new GenericObjectServiceException(ID_EXISTS(pk.toString()));
//        }
//        if (t.getCantid().compareTo(zero) <= 0 || t.getTypinv()<=0 || t.getCodart().isEmpty()){
//            throw new GenericObjectServiceException("You cannot register an item without item and/or quantity");
//        }
//        if (t.getImpafecto().compareTo(zero)<= 0){
//            throw new GenericObjectServiceException("Cannot register an item with zero amount");
//        }
//        t.setImpsubtotal(t.getImpafecto().add(t.getImpinafecto()).add(t.getImpexonerado()).add(t.getImpgratuito()));
//        t.setImpdesctotal(t.getImpdesc01().multiply(t.getImpdesc02()).multiply(t.getImpdesc03()).multiply(t.getImpdesc04()));
//        t.setImpdesctotal(t.getImpsubtotal().multiply(t.getImpdesctotal()));
//        t.setImpsubtotal(t.getImpsubtotal().subtract(t.getImpdesctotal()));
//        t.setImpigv(t.getImpsubtotal().multiply(igv));
//        t.setImpisc(t.getImpsubtotal().multiply(isc));
//        t.setImptotal(t.getImpsubtotal().add(t.getImpigv()).add(t.getImpisc()));
//        return new ApiResponseObject<DocumentDetailEntity>(1,"Ok", Optional.of(this.documentDetailRepository.save(t)));
//    }

    @Override
    public ApiResponseObject<DocumentDetailEntity> registerDocumentDetail(DocumentDetailEntity t) throws GenericObjectServiceException {
        try {
            Map<String, Object> obj = this.documentDetailRepository.registerDocumentHeader(t.toJson(), 0L,0,"","");
//			for (String key : obj.keySet()) {
//				Object value = obj.get(key);
//				System.out.println("Clave: " + key + ", Valor: " + value);
//			}
//            if ((Integer) obj.get("out_code") >= 0) {
//                Optional<DocumentHeaderEntity> tmp = this.documentDetailRepository.findById((Long) obj.get("out_numite"));
//                return new ApiResponseObject<DocumentDetailEntity>((Integer) obj.get("out_code"), obj.get("out_message").toString(), obj.get("out_log").toString(),tmp);
//            }
            return new ApiResponseObject<DocumentDetailEntity>((Integer) obj.get("out_code"), obj.get("out_message").toString(), obj.get("out_log").toString(),Optional.empty());
        } catch (GenericProcedureException | JsonProcessingException e) {
            throw new GenericObjectServiceException(e);
        }
    }

}
