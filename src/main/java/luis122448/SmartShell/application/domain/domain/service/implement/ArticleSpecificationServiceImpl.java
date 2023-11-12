package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.service.service.ArticleSpecificationService;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleSpecificationEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleSpecificationPK;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleSpecificationRepository;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleSpecificationServiceImpl implements ArticleSpecificationService {

    private final ArticleSpecificationRepository articleSpecificationRepository;

    public ArticleSpecificationServiceImpl(ArticleSpecificationRepository articleSpecificationRepository) {
        this.articleSpecificationRepository = articleSpecificationRepository;
    }

    @Override
    public ApiResponseList<ArticleSpecificationEntity> findAll(ArticleSpecificationEntity articleSpecificationEntity) throws GenericListServiceException {
        List<ArticleSpecificationEntity> articleSpecificationEntityList = this.articleSpecificationRepository.findByTypinv(articleSpecificationEntity.getTypinv());
        return new ApiResponseList<ArticleSpecificationEntity>(1,"OK", Optional.of(articleSpecificationEntityList));
    }

    @Override
    public ApiResponseList<ArticleSpecificationEntity> findByLike(ArticleSpecificationEntity articleSpecificationEntity) throws GenericListServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ArticleSpecificationEntity> findById(ArticleSpecificationEntity articleSpecificationEntity) throws GenericObjectServiceException {
        Optional<ArticleSpecificationEntity> articleSpecificationEntityOptional = this.articleSpecificationRepository.findById(new ArticleSpecificationPK(articleSpecificationEntity.getTypinv(),articleSpecificationEntity.getTypspe()));
        return new ApiResponseObject<ArticleSpecificationEntity>(1,"OK",articleSpecificationEntityOptional);
    }

    @Override
    public ApiResponseObject<ArticleSpecificationEntity> save(ArticleSpecificationEntity articleSpecificationEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ArticleSpecificationEntity> update(ArticleSpecificationEntity articleSpecificationEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ArticleSpecificationEntity> delete(ArticleSpecificationEntity articleSpecificationEntity) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ArticleSpecificationEntity> undelete(ArticleSpecificationEntity articleSpecificationEntity) throws GenericObjectServiceException {
        return null;
    }
}
