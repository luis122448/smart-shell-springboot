package luis122448.SmartShell.application.domain.domain.service.service;

import luis122448.SmartShell.application.domain.domain.model.ListPriceArticleDTO;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericPageableService;
import luis122448.SmartShell.application.domain.domain.service.generic.GenericService;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceArticleEntity;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;

public interface ListPriceArticleService extends GenericService<ListPriceArticleEntity, ListPriceArticleDTO>, GenericPageableService<ListPriceArticleEntity, ListPriceArticleDTO> {
    ApiResponseList<ListPriceArticleEntity> findByCodlistprice(Integer codlistprice, String status) throws GenericListServiceException;
    ApiResponseList<ListPriceArticleEntity> findByCodart(String codart, String status) throws GenericListServiceException;
}
