package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.service.generic.GenericSearchService;
import luis122448.SmartShell.application.domain.persistence.entity.FormatCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.FormatCommercialDocumentPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface FormatCommercialDocumentService extends GenericSearchService<FormatCommercialDocumentEntity, FormatCommercialDocumentPK> {

    ApiResponseList<FormatCommercialDocumentEntity> findAll() throws GenericListServiceException;
}
