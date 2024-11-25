package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.ArticleAttachedDTO;
import luis122448.SmartShell.application.domain.domain.service.service.ArticleAttachedService;
import luis122448.SmartShell.application.domain.persistence.entity.ArticleAttachedEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticleAttachedPK;
import luis122448.SmartShell.application.domain.persistence.mapper.ArticleAttachedMapper;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleAttachedRepository;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_NOT_EXISTS;

@Service
public class ArticleAttachedServiceImpl implements ArticleAttachedService {
    private final ArticleAttachedRepository articleAttachedRepository;
    private final ArticleAttachedMapper articleAttachedMapper;
    private final SecurityContextInitializer securityContextInitializer;
    public ArticleAttachedServiceImpl(ArticleAttachedRepository articleAttachedRepository, ArticleAttachedMapper articleAttachedMapper, SecurityContextInitializer securityContextInitializer) {
        this.articleAttachedRepository = articleAttachedRepository;
        this.articleAttachedMapper = articleAttachedMapper;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<ArticleAttachedEntity> findByCodart(String codart, String status) throws GenericListServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        List<ArticleAttachedEntity> entityList = this.articleAttachedRepository.findByIdcompanyAndCodart(idCompany,codart,status);
        if (entityList.isEmpty()){
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(entityList));
    }

    @Override
    public ApiResponseObject<ArticleAttachedEntity> findById(ArticleAttachedDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        ArticleAttachedPK id = new ArticleAttachedPK(idCompany,dto.getCodart(),dto.getTypspe());
        Optional<ArticleAttachedEntity> searchEntity = this.articleAttachedRepository.findById(id);
        if (searchEntity.isEmpty()){
            throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
        }
        return new ApiResponseObject<>(searchEntity);
    }

}
