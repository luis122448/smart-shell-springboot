package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.service.generic.GenericCrudService;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericSearchService;
import luis122448.SmartShell.application.domain.persistence.entity.SerieCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.SerieCommercialDocumentPK;

public interface SerieCommercialDocumentService extends GenericCrudService<SerieCommercialDocumentEntity, SerieCommercialDocumentPK>, GenericSearchService<SerieCommercialDocumentEntity, SerieCommercialDocumentPK> {

}
