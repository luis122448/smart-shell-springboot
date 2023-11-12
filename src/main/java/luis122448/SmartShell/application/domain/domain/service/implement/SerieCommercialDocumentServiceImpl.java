package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.SerieCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.repository.SerieCommercialDocumentRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.SerieCommercialDocumentService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import java.util.Optional;

@Service
public class SerieCommercialDocumentServiceImpl implements SerieCommercialDocumentService {

	private final SerieCommercialDocumentRepository serieCommercialDocumentRepository;
	public SerieCommercialDocumentServiceImpl(SerieCommercialDocumentRepository serieCommercialDocumentRepository) {
		super();
		this.serieCommercialDocumentRepository = serieCommercialDocumentRepository;
	}

	@Override
	public ApiResponseList<SerieCommercialDocumentEntity> findAll(SerieCommercialDocumentEntity t)
			throws GenericListServiceException {
		return new ApiResponseList<SerieCommercialDocumentEntity>(1,"Ok",Optional.of(this.serieCommercialDocumentRepository.findAll()));
	}

	@Override
	public ApiResponseList<SerieCommercialDocumentEntity> findByLike(SerieCommercialDocumentEntity t)
			throws GenericListServiceException {
		return new ApiResponseList<SerieCommercialDocumentEntity>(1,"Ok",Optional.ofNullable(this.serieCommercialDocumentRepository.findByTypcomdoc(t.getTypcomdoc())));
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> findById(SerieCommercialDocumentEntity t) throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> save(SerieCommercialDocumentEntity t)
			throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> update(SerieCommercialDocumentEntity t)
			throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> delete(SerieCommercialDocumentEntity t)
			throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> undelete(SerieCommercialDocumentEntity t)
			throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
