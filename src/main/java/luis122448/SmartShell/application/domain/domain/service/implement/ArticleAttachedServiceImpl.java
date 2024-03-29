package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
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
    private final SecurityContextInitializer securityContextInitializer;
    public ArticleAttachedServiceImpl(ArticleAttachedRepository articleAttachedRepository, SecurityContextInitializer securityContextInitializer) {
        this.articleAttachedRepository = articleAttachedRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<ArticleAttachedEntity> findByLike(ArticleAttachedEntity articleAttachedEntity) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<ArticleAttachedEntity> articleAttachedEntityList = this.articleAttachedRepository.findByIdcompanyAndCodart(idcompany,articleAttachedEntity.getCodart());
        return new ApiResponseList<ArticleAttachedEntity>(Optional.of(articleAttachedEntityList));
    }

    @Override
    public ApiResponseObject<ArticleAttachedEntity> findById(ArticleAttachedPK articleAttachedPK) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        return new ApiResponseObject<ArticleAttachedEntity>(this.articleAttachedRepository.findById(new ArticleAttachedPK(idcompany,articleAttachedPK.getCodart(),articleAttachedPK.getTypspe())));
    }

}
