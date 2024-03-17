package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.SellerEntity;
import luis122448.SmartShell.application.domain.persistence.repository.SellerRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.SellerService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
	private final SellerRepository sellerRepository;
	private final SecurityContextInitializer securityContextInitializer;
	public SellerServiceImpl(SellerRepository sellerRepository, SecurityContextInitializer securityContextInitializer) {
		this.sellerRepository = sellerRepository;
		this.securityContextInitializer = securityContextInitializer;
	}

	@Override
	public ApiResponseList<SellerEntity> findAll() throws GenericListServiceException {
		Integer idcompany = securityContextInitializer.getIdCompany();
		List<SellerEntity> lst = this.sellerRepository.findByIdcompany(idcompany);
		if (lst.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<>(Optional.of(lst));
	}

	@Override
	public ApiResponseList<SellerEntity> findByLike(SellerEntity t) throws GenericListServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<SellerEntity> findById(SellerEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<SellerEntity> save(SellerEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<SellerEntity> update(SellerEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<SellerEntity> delete(SellerEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<SellerEntity> undelete(SellerEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
