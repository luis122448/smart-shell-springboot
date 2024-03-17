package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.service.generic.GenericCrudService;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericSearchService;
import luis122448.SmartShell.application.domain.persistence.entity.SerieCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.SerieCommercialDocumentPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface SerieCommercialDocumentService extends GenericCrudService<SerieCommercialDocumentEntity, SerieCommercialDocumentPK>, GenericSearchService<SerieCommercialDocumentEntity, SerieCommercialDocumentPK> {
    ApiResponseList<SerieCommercialDocumentEntity> findAll() throws GenericListServiceException;
}
