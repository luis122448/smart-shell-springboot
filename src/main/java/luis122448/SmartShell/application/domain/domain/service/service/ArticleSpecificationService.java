package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.ArticleSpecificationDTO;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleSpecificationEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface ArticleSpecificationService {
    ApiResponseList<ArticleSpecificationEntity> findByTypinv(Integer typinv, String status) throws GenericListServiceException;
    ApiResponseObject<ArticleSpecificationEntity> findById(ArticleSpecificationDTO dto) throws GenericObjectServiceException;
}
