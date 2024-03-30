package luis122448.SmartShell.application.domain.domain.service.service.view;

import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.application.domain.persistence.entity.CompanyInfoEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface CompanyInfoService{
    ApiResponseObject<CompanyInfoEntity> findByIdcompany() throws GenericListServiceException;
    ApiResponseObject<CompanyInfoEntity> update(CompanyInfoEntity companyInfoEntity) throws GenericObjectServiceException;
}
