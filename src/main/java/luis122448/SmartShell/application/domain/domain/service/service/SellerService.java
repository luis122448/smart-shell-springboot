package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.SellerDTO;
import luis122448.SmartShell.application.domain.persistence.entity.SellerEntity;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface SellerService extends GenericService<SellerEntity, SellerDTO>{
    ApiResponseList<SellerEntity> findAll(String status) throws GenericListServiceException;
}
