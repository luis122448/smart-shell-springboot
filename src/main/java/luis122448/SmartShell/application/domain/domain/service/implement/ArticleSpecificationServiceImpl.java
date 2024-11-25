package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.ArticleSpecificationDTO;
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

import static luis122448.SmartShell.util.constant.MESSAGEConstants.ID_NOT_EXISTS;

@Service
public class ArticleSpecificationServiceImpl implements ArticleSpecificationService {
    private final ArticleSpecificationRepository articleSpecificationRepository;
    private final SecurityContextInitializer securityContextInitializer;
    public ArticleSpecificationServiceImpl(ArticleSpecificationRepository articleSpecificationRepository, SecurityContextInitializer securityContextInitializer) {
        this.articleSpecificationRepository = articleSpecificationRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<ArticleSpecificationEntity> findByTypinv(Integer typinv, String status) throws GenericListServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        List<ArticleSpecificationEntity> entityList = this.articleSpecificationRepository.findByIdcompanyAndTypinv(idCompany,typinv,status);
        if (entityList.isEmpty()){
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(entityList));
    }

    @Override
    public ApiResponseObject<ArticleSpecificationEntity> findById(ArticleSpecificationDTO dto) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        ArticleSpecificationPK id = new ArticleSpecificationPK(idcompany,dto.getTypinv(),dto.getTypspe());
        Optional<ArticleSpecificationEntity> searchEntity = this.articleSpecificationRepository.findById(id);
        if (searchEntity.isEmpty()){
            throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
        }
        return new ApiResponseObject<>(searchEntity);
    }

}
