package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.service.service.ArticleSpecificationService;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleSpecificationEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.ArticleSpecificationPK;
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
    private final SecurityContextInitializer securityContextInitializer;
    public ArticleSpecificationServiceImpl(ArticleSpecificationRepository articleSpecificationRepository, SecurityContextInitializer securityContextInitializer) {
        this.articleSpecificationRepository = articleSpecificationRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<ArticleSpecificationEntity> findByLike(ArticleSpecificationEntity articleSpecificationEntity) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<ArticleSpecificationEntity> articleSpecificationEntityList = this.articleSpecificationRepository.findByIdcompanyAndTypinv(idcompany,articleSpecificationEntity.getTypinv());
        return new ApiResponseList<ArticleSpecificationEntity>(Optional.of(articleSpecificationEntityList));
    }

    @Override
    public ApiResponseObject<ArticleSpecificationEntity> findById(ArticleSpecificationPK articleSpecificationPK) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        Optional<ArticleSpecificationEntity> articleSpecificationEntityOptional = this.articleSpecificationRepository.findById(new ArticleSpecificationPK(idcompany,articleSpecificationPK.getTypinv(),articleSpecificationPK.getTypspe()));
        return new ApiResponseObject<ArticleSpecificationEntity>(articleSpecificationEntityOptional);
    }

}
