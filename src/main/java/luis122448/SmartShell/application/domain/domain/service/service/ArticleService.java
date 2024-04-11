package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.service.generic.GenericPageableService;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericValidations;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface ArticleService extends GenericService<ArticleEntity>, GenericPageableService<ArticleEntity>, GenericValidations<ArticleEntity> {

    ApiResponseList<ArticleEntity> findByName(String name) throws GenericListServiceException;

}
