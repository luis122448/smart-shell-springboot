package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.SerieCommercialDocumentDTO;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericCrudService;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericSearchService;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.application.domain.persistence.entity.SerieCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.SerieCommercialDocumentPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface SerieCommercialDocumentService extends GenericService<SerieCommercialDocumentEntity, SerieCommercialDocumentDTO> {
    ApiResponseList<SerieCommercialDocumentEntity> findAll(String status) throws GenericListServiceException;
    ApiResponseList<SerieCommercialDocumentEntity> findByTypcomdoc(Integer typcomdoc, String status) throws GenericListServiceException;
}
