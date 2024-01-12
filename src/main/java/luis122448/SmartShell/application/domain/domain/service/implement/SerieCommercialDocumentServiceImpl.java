package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.persistence.entity.primary.SerieCommercialDocumentPK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.entity.SerieCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.repository.SerieCommercialDocumentRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.SerieCommercialDocumentService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;

import java.util.List;
import java.util.Optional;

@Service
public class SerieCommercialDocumentServiceImpl implements SerieCommercialDocumentService {

	private final SerieCommercialDocumentRepository serieCommercialDocumentRepository;
	public SerieCommercialDocumentServiceImpl(SerieCommercialDocumentRepository serieCommercialDocumentRepository) {
		super();
		this.serieCommercialDocumentRepository = serieCommercialDocumentRepository;
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> save(SerieCommercialDocumentEntity serieCommercialDocumentEntity) throws GenericObjectServiceException {
		SerieCommercialDocumentEntity tmp = this.serieCommercialDocumentRepository.save(serieCommercialDocumentEntity);
		return new ApiResponseObject<>(Optional.of(tmp));
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> update(SerieCommercialDocumentEntity serieCommercialDocumentEntity) throws GenericObjectServiceException {
		SerieCommercialDocumentEntity tmp = this.serieCommercialDocumentRepository.save(serieCommercialDocumentEntity);
		return new ApiResponseObject<>(Optional.of(tmp));
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> delete(SerieCommercialDocumentPK serieCommercialDocumentPK) throws GenericObjectServiceException {
		this.serieCommercialDocumentRepository.deleteById(serieCommercialDocumentPK);
		return new ApiResponseObject<>(Optional.empty());
	}

	@Override
	public ApiResponseList<SerieCommercialDocumentEntity> findAll() throws GenericListServiceException {
		List<SerieCommercialDocumentEntity> list = this.serieCommercialDocumentRepository.findAll();
		if (list.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<>(Optional.of(list));
	}

	@Override
	public ApiResponseList<SerieCommercialDocumentEntity> findByLike(SerieCommercialDocumentEntity serieCommercialDocumentEntity) throws GenericListServiceException {
		List<SerieCommercialDocumentEntity> list = this.serieCommercialDocumentRepository.findByTypcomdoc(serieCommercialDocumentEntity.getTypcomdoc());
		if (list.isEmpty()) {
			throw new GenericListServiceException(404);
		}
		return new ApiResponseList<>(Optional.of(list));
	}

	@Override
	public ApiResponseObject<SerieCommercialDocumentEntity> findById(SerieCommercialDocumentPK serieCommercialDocumentPK) throws GenericObjectServiceException {
		return new ApiResponseObject<>(serieCommercialDocumentRepository.findById(serieCommercialDocumentPK));
	}

}
