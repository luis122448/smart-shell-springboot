package luis122448.SmartShell.application.domain.domain.service.implement.view;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import luis122448.SmartShell.application.domain.persistence.view.TypePaymentConditionViewEntity;
import luis122448.SmartShell.application.domain.persistence.repository.procedure.view.TipoCondicionPagoViewRepository;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.view.TipoCondicionPagoViewService;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponsePage;

import java.util.Optional;

@Service
public class TipoCondicionPagoViewServiceImpl implements TipoCondicionPagoViewService{

	private TipoCondicionPagoViewRepository tipoCondicionPagoViewRepository;
	
	public TipoCondicionPagoViewServiceImpl(TipoCondicionPagoViewRepository tipoCondicionPagoViewRepository) {
		super();
		this.tipoCondicionPagoViewRepository = tipoCondicionPagoViewRepository;
	}

	@Override
	public ApiResponseList<TypePaymentConditionViewEntity> findByLike(TypePaymentConditionViewEntity t)
			throws GenericObjectServiceException {
		try {
			return new ApiResponseList<TypePaymentConditionViewEntity>((short) 1,"Ok", Optional.of(this.tipoCondicionPagoViewRepository.findByCodintcom(t.getCodbuspar())));
		} catch (Exception e) {
			return new ApiResponseList<TypePaymentConditionViewEntity>((short) -2,e.getMessage(),null);
		}
	}

	@Override
	public ApiResponsePage<TypePaymentConditionViewEntity> findByPage(TypePaymentConditionViewEntity t, Pageable p)
			throws GenericObjectServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
