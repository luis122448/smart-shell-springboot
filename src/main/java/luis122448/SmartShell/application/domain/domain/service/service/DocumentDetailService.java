package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.DocumentDetailEntity;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface DocumentDetailService {

//    ApiResponseObject<DocumentDetailEntity> save(DocumentDetailEntity t) throws GenericObjectServiceException;
    ApiResponseObject<DocumentDetailEntity> registerDocumentDetail(DocumentDetailEntity t) throws GenericObjectServiceException;
//    ApiResponseList<DocumentDetailEntity> findAll(Long numint, Long numite);

}
