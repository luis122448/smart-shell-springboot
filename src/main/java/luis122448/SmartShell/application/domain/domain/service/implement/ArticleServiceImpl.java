package luis122448.SmartShell.application.domain.domain.service.implement;

import static luis122448.SmartShell.util.code.Utils.copyNonNullProperties;

import java.util.List;
import java.util.Optional;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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
	public ArticleServiceImpl(ArticleRepository articleRepository) {
		super();
		this.articleRepository = articleRepository;
	}

	@Override
	public ApiResponseList<ArticleEntity> findAll(ArticleEntity t) throws GenericListServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseList<ArticleEntity> findByLike(ArticleEntity t) throws GenericListServiceException {
		if (!t.getCodart().isEmpty()) {
			List<ArticleEntity> list = this.articleRepository.findByCodart(t.getCodart());
			if(list.isEmpty()) {
				throw new GenericListServiceException(404);
			}
			return new ApiResponseList<ArticleEntity>(Optional.of(list));
		} else if(!t.getDescri().isEmpty()) {
			List<ArticleEntity> list = this.articleRepository.findByDescri(t.getDescri());
			if(list.isEmpty()) {
				throw new GenericListServiceException(404);
			}
			return new ApiResponseList<ArticleEntity>(Optional.of(list));
		} else {
			return new ApiResponseList<ArticleEntity>(-2,"No Case",Optional.empty());
		}
	}

	@Override
	public ApiResponseObject<ArticleEntity> findById(ArticleEntity t) throws GenericObjectServiceException {
		return new ApiResponseObject<ArticleEntity>(1,"Ok",this.articleRepository.findById(t.getCodart()));
	}

	@Override
	public ApiResponseObject<ArticleEntity> save(ArticleEntity t) throws GenericObjectServiceException {
		if (this.articleRepository.existsById(t.getCodart())) {
			return new ApiResponseObject<ArticleEntity>(-2,ID_EXISTS(t.getCodart()),Optional.empty());
		}
		t.setStatus("S");
		return new ApiResponseObject<ArticleEntity>(1,"Ok",Optional.of(this.articleRepository.save(t)));
	}

	@Override
	public ApiResponseObject<ArticleEntity> update(ArticleEntity t) throws GenericObjectServiceException {
		if (!this.articleRepository.existsById(t.getCodart())) {
			return new ApiResponseObject<ArticleEntity>(-2,ID_NOT_EXISTS(t.getCodart()),Optional.empty());
		}
		ArticleEntity tmp = this.articleRepository.findById(t.getCodart()).orElseThrow();
		t.setStatus("Y");
		copyNonNullProperties(t, tmp);
		return new ApiResponseObject<ArticleEntity>(1,"Ok",Optional.of(this.articleRepository.save(tmp)));
	}

	@Override
	public ApiResponseObject<ArticleEntity> delete(ArticleEntity t) throws GenericObjectServiceException {
		if (!this.articleRepository.existsById(t.getCodart())) {
			return new ApiResponseObject<ArticleEntity>(-2,ID_NOT_EXISTS(t.getCodart()),Optional.empty());
		}
		ArticleEntity tmp = this.articleRepository.findById(t.getCodart()).orElseThrow();
		tmp.setStatus("N");
		return new ApiResponseObject<ArticleEntity>(1,"Ok",Optional.of(this.articleRepository.save(tmp)));
	}

	@Override
	public ApiResponsePage<ArticleEntity> findByPage(ArticleEntity t, Pageable p) throws GenericPageServiceException {
		return new ApiResponsePage<ArticleEntity>(1, "Ok",Optional.ofNullable(this.articleRepository.findByPage(t.getCodart(), t.getDescri(), p)));
	}

	@Override
	public ApiResponseObject<ArticleEntity> undelete(ArticleEntity t) throws GenericObjectServiceException {
		if (!this.articleRepository.existsById(t.getCodart())) {
			return new ApiResponseObject<ArticleEntity>(-2,ID_NOT_EXISTS(t.getCodart()),Optional.empty());
		}
		ArticleEntity tmp = this.articleRepository.findById(t.getCodart()).orElseThrow();
		tmp.setStatus("Y");
		return new ApiResponseObject<ArticleEntity>(1,"Ok",Optional.of(tmp));
	}

	@Override
	@Cacheable(value="shortTime")
	public ApiResponseObject<ArticleEntity> isAvailable(ArticleEntity articleEntity) throws GenericObjectServiceException {
		if (this.articleRepository.existsById(articleEntity.getCodart())) {
			throw new GenericObjectServiceException(ID_EXISTS(articleEntity.getCodart()));
		}
		return new ApiResponseObject<ArticleEntity>(-1,"Ok",Optional.empty());
	}
}
