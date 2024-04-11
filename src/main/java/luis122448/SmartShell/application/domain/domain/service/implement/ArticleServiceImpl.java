package luis122448.SmartShell.application.domain.domain.service.implement;

import static luis122448.SmartShell.util.code.Utils.copyNonNullProperties;

import java.util.List;
import java.util.Optional;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticlePK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ArticleService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.*;

@Service
public class ArticleServiceImpl implements ArticleService {
	private final ArticleRepository articleRepository;
	private final SecurityContextInitializer securityContextInitializer;
	public ArticleServiceImpl(ArticleRepository articleRepository, SecurityContextInitializer securityContextInitializer) {
		this.articleRepository = articleRepository;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseList<ArticleEntity> findByLike(ArticleEntity t) throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		List<ArticleEntity> list = List.of();
		if (!t.getCodart().isEmpty()) {
			list = this.articleRepository.findByCodart(idcompany,t.getCodart());
		} else if (!t.getDescri().isEmpty()) {
			list = this.articleRepository.findByDescri(idcompany,t.getDescri());
		} else {
			return new ApiResponseList<>(-2, "No Case", Optional.empty());
		}
		if (list.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<ArticleEntity>(Optional.of(list));
	}

	@Override
	public ApiResponseList<ArticleEntity> findByName(String name) throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		List<ArticleEntity> list = this.articleRepository.findByDName(idcompany,name);
		if (list.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<ArticleEntity>(Optional.of(list));
	}

	@Override
	public ApiResponseObject<ArticleEntity> findById(ArticleEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		Optional<ArticleEntity> articleEntity = this.articleRepository.findById(new ArticlePK(idcompany,t.getCodart()));
		if (articleEntity.isEmpty()) {
			throw new GenericObjectServiceException(ID_NOT_EXISTS(t.getCodart()));
		}
		return new ApiResponseObject<ArticleEntity>(articleEntity);
	}

	@Override
	public ApiResponseObject<ArticleEntity> save(ArticleEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		if (this.articleRepository.existsById(new ArticlePK(idcompany,t.getCodart()))) {
			throw new GenericObjectServiceException(ID_EXISTS(t.getCodart()));
		}
		t.setStatus("S");
		return new ApiResponseObject<ArticleEntity>(1,"Ok",Optional.of(this.articleRepository.save(t)));
	}

	@Override
	public ApiResponseObject<ArticleEntity> update(ArticleEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		ArticleEntity articleEntity = this.articleRepository.findById(new ArticlePK(idcompany,t.getCodart())).orElseThrow(
				() -> new GenericObjectServiceException(ID_NOT_EXISTS(t.getCodart()))
		);
		t.setStatus("Y");
		copyNonNullProperties(t, articleEntity);
		return new ApiResponseObject<ArticleEntity>(Optional.of(this.articleRepository.save(articleEntity)));
	}

	@Override
	public ApiResponseObject<ArticleEntity> delete(ArticleEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		ArticleEntity articleEntity = this.articleRepository.findById(new ArticlePK(idcompany,t.getCodart())).orElseThrow(
				() -> new GenericObjectServiceException(ID_NOT_EXISTS(t.getCodart()))
		);
		articleEntity.setStatus("N");
		return new ApiResponseObject<ArticleEntity>(Optional.of(this.articleRepository.save(articleEntity)));
	}

	@Override
	public ApiResponsePage<ArticleEntity> findByPage(ArticleEntity t, Pageable p) throws GenericPageServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		return new ApiResponsePage<ArticleEntity>(Optional.ofNullable(this.articleRepository.findByPage(idcompany,t.getCodart(), t.getDescri(), p)));
	}

	@Override
	public ApiResponseObject<ArticleEntity> undelete(ArticleEntity t) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		ArticleEntity articleEntity = this.articleRepository.findById(new ArticlePK(idcompany,t.getCodart())).orElseThrow(
				() -> new GenericObjectServiceException(ID_NOT_EXISTS(t.getCodart()))
		);
		articleEntity.setStatus("Y");
		return new ApiResponseObject<ArticleEntity>(Optional.of(articleEntity));
	}

	@Override
	@Cacheable(value="shortTime")
	public ApiResponseObject<ArticleEntity> isAvailable(ArticleEntity articleEntity) throws GenericObjectServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		if (this.articleRepository.existsById(new ArticlePK(idcompany,articleEntity.getCodart()))){
			throw new GenericObjectServiceException(ID_EXISTS(articleEntity.getCodart()));
		}
		return new ApiResponseObject<ArticleEntity>(Optional.empty());
	}
}
