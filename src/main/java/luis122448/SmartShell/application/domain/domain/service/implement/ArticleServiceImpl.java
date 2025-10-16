package luis122448.SmartShell.application.domain.domain.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.ArticleDTO;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ArticlePK;
import luis122448.SmartShell.application.domain.persistence.mapper.ArticleMapper;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.ArticleEntity;
import luis122448.SmartShell.application.domain.persistence.repository.ArticleRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ArticleService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import static luis122448.SmartShell.util.code.Utils.*;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.*;

@Service
public class ArticleServiceImpl implements ArticleService {
	private final ArticleRepository articleRepository;
	private final ArticleMapper articleMapper;
	private final SecurityContextInitializer securityContextInitializer;
	public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper, SecurityContextInitializer securityContextInitializer) {
		this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseObject<ArticleEntity> exist(Integer typinv, String codart) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		if (this.articleRepository.existsById(new ArticlePK(idCompany,codart))) {
			return new ApiResponseObject<>(-2, ID_EXISTS(codart), Optional.empty());
		}
		return new ApiResponseObject<>(Optional.empty());
	}

	@Override
	@Cacheable(value="shortTime")
	public ApiResponseObject<ArticleEntity> isAvailable(ArticleDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		ArticlePK id = new ArticlePK(idCompany,dto.getCodart());
		if (this.articleRepository.existsById(id)){
			throw new GenericObjectServiceException(ID_EXISTS(id.toString()));
		}
		return new ApiResponseObject<>(Optional.empty());
	}

	@Override
	public ApiResponseList<ArticleEntity> findByName(String name) throws GenericListServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		List<ArticleEntity> listEntity = this.articleRepository.findByDName(idCompany,name);
		if (listEntity.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<>(Optional.of(listEntity));
	}

	@Override
	public ApiResponsePage<ArticleEntity> findByPage(ArticleDTO dto, Pageable p) throws GenericPageServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		Page<ArticleEntity> pageEntity = this.articleRepository.findByPage(idCompany,dto.getTypinv(), dto.getCodart(), dto.getDescri(), dto.getStatus(), p);
		if (pageEntity.isEmpty()) {
			throw new GenericPageServiceException(404);
		}
		return new ApiResponsePage<>(Optional.of(pageEntity));
	}

	@Override
	public ApiResponseObject<ArticleEntity> findById(ArticleDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		ArticlePK id = new ArticlePK(idCompany,dto.getCodart());
		Optional<ArticleEntity> searchEntity = this.articleRepository.findById(id);
		if (searchEntity.isEmpty()) {
			throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
		}
		return new ApiResponseObject<>(searchEntity);
	}

	@Override
	public ApiResponseObject<ArticleEntity> save(ArticleDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		String codUser = securityContextInitializer.getCodUser();
		ArticlePK id = new ArticlePK(idCompany,dto.getCodart());
		if (this.articleRepository.existsById(id)) {
			throw new GenericObjectServiceException(ID_EXISTS(dto.toString()));
		}
		ArticleEntity newEntity = this.articleMapper.toArticleEntity(dto);
		newEntity.setIdcompany(idCompany);
		newEntity.setCreateby(codUser);
		newEntity.setCreateat(LocalDateTime.now());
		ArticleEntity savedEntity = this.articleRepository.save(newEntity);
		return new ApiResponseObject<>(Optional.of(savedEntity));
	}

	@Override
	public ApiResponseObject<ArticleEntity> update(ArticleDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		String codUser = securityContextInitializer.getCodUser();
		ArticlePK id = new ArticlePK(idCompany,dto.getCodart());
		ArticleEntity existingEntity = this.articleRepository.findById(id).orElseThrow(
				() -> new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()))
		);
		ArticleEntity updateEntity = this.articleMapper.toArticleEntity(dto);
		updateEntity.setIdcompany(idCompany);
		updateEntity.setUpdateby(codUser);
		updateEntity.setUpdateat(LocalDateTime.now());
		copyNonNullProperties(existingEntity, updateEntity);
		ArticleEntity savedEntity = this.articleRepository.save(updateEntity);
		return new ApiResponseObject<>(Optional.of(savedEntity));
	}

	@Override
	public ApiResponseObject<ArticleEntity> delete(ArticleDTO dto) throws GenericObjectServiceException {
		Integer idCompany = securityContextInitializer.getIdCompany();
		ArticlePK id = new ArticlePK(idCompany,dto.getCodart());
		this.articleRepository.deleteById(id);
		return new ApiResponseObject<>(Optional.empty());
	}

}
