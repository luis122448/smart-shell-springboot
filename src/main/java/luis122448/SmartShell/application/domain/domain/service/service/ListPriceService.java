package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.ListPriceDTO;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface ListPriceService extends GenericService<ListPriceEntity, ListPriceDTO> {
    ApiResponseList<ListPriceEntity> findAll(String status) throws GenericListServiceException;
}
