package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.service.service.ArticleAttachedService;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleAttachedEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleAttachedPK;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleAttachedRepository;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleAttachedServiceImpl implements ArticleAttachedService {

    private final ArticleAttachedRepository articleAttachedRepository;
    public ArticleAttachedServiceImpl(ArticleAttachedRepository articleAttachedRepository) {
        this.articleAttachedRepository = articleAttachedRepository;
    }

    @Override
    public ApiResponseList<ArticleAttachedEntity> findAll(ArticleAttachedEntity articleAttachedEntity) throws GenericListServiceException {
        List<ArticleAttachedEntity> articleAttachedEntityList = this.articleAttachedRepository.findByCodart(articleAttachedEntity.getCodart());
        return new ApiResponseList<ArticleAttachedEntity>(Optional.of(articleAttachedEntityList));
    }

    @Override
    public ApiResponseList<ArticleAttachedEntity> findByLike(ArticleAttachedEntity articleAttachedEntity) throws GenericListServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ArticleAttachedEntity> findById(ArticleAttachedEntity articleAttachedEntity) throws GenericObjectServiceException {
        return new ApiResponseObject<ArticleAttachedEntity>(this.articleAttachedRepository.findById(new ArticleAttachedPK(articleAttachedEntity.getCodart(),articleAttachedEntity.getTypspe())));
    }

    @Override
    public ApiResponseObject<ArticleAttachedEntity> save(ArticleAttachedEntity articleAttachedEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ArticleAttachedEntity> update(ArticleAttachedEntity articleAttachedEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ArticleAttachedEntity> delete(ArticleAttachedEntity articleAttachedEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ArticleAttachedEntity> undelete(ArticleAttachedEntity articleAttachedEntity) throws GenericObjectServiceException {
        return null;
    }
}
