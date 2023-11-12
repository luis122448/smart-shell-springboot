package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.service.generic.GenericPageableService;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericValidations;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;

public interface ArticleService extends GenericService<ArticleEntity>, GenericPageableService<ArticleEntity>, GenericValidations<ArticleEntity> {

}
