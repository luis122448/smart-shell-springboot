package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.SellerEntity;
import luis122448.SmartShell.application.domain.persistence.repository.SellerRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.SellerService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

	private final SellerRepository sellerRepository;
	
	public SellerServiceImpl(SellerRepository sellerRepository) {
		super();
		this.sellerRepository = sellerRepository;
	}

	@Override
	public ApiResponseList<SellerEntity> findAll(SellerEntity t) throws GenericListServiceException {
		return new ApiResponseList<SellerEntity>(1,"Ok",Optional.of(this.sellerRepository.findAll()));
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
