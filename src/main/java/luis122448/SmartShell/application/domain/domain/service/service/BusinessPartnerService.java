package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.BusinessPartnerDTO;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericPageableService;
import luis122448.SmartShell.application.domain.persistence.entity.BusinessPartnerEntity;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;

public interface BusinessPartnerService extends GenericService<BusinessPartnerEntity, BusinessPartnerDTO>, GenericPageableService<BusinessPartnerEntity, BusinessPartnerDTO> {

}
