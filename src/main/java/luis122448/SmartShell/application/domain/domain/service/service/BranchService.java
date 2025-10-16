package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.BranchDTO;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.application.domain.persistence.entity.BranchEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface BranchService extends GenericService<BranchEntity, BranchDTO> {
    ApiResponseList<BranchEntity> findAll(String status) throws GenericListServiceException;
}
