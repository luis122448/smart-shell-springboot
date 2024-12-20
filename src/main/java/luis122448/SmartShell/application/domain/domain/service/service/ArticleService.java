package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.ArticleDTO;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericPageableService;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericValidations;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

public interface ArticleService extends GenericService<ArticleEntity, ArticleDTO>, GenericPageableService<ArticleEntity, ArticleDTO>, GenericValidations<ArticleEntity, ArticleDTO> {

    ApiResponseList<ArticleEntity> findByName(String name) throws GenericListServiceException;
    ApiResponseObject<ArticleEntity> exist(Integer typinv, String codart) throws GenericObjectServiceException;

}
