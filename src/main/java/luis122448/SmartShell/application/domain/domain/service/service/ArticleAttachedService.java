package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.ArticleAttachedDTO;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleAttachedEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface ArticleAttachedService {
    ApiResponseList<ArticleAttachedEntity> findByCodart(String codart, String status) throws GenericListServiceException;
    ApiResponseObject<ArticleAttachedEntity> findById(ArticleAttachedDTO dto) throws GenericObjectServiceException;
}
