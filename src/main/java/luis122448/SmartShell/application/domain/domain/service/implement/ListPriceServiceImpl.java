package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ListPricePK;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ListPriceService;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceEntity;
import luis122448.SmartShell.application.domain.persistence.repository.ListPriceRepository;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.*;
import java.util.Optional;

@Service
public class ListPriceServiceImpl implements ListPriceService {
    private final ListPriceRepository listPriceRepository;
    private final SecurityContextInitializer securityContextInitializer;
    public ListPriceServiceImpl(ListPriceRepository listPriceRepository, SecurityContextInitializer securityContextInitializer) {
        this.listPriceRepository = listPriceRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<ListPriceEntity> findAll() throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        return new ApiResponseList<ListPriceEntity>(Optional.of(this.listPriceRepository.findByIdcompany(idcompany)));
    }

    @Override
    public ApiResponseList<ListPriceEntity> findByLike(ListPriceEntity listPriceEntity) throws GenericListServiceException {
        throw new GenericListServiceException(405);
    }

    @Override
    public ApiResponseObject<ListPriceEntity> findById(ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        return new ApiResponseObject<ListPriceEntity>(this.listPriceRepository.findById(new ListPricePK(idcompany,listPriceEntity.getCodlistprice())));
    }

    @Override
    public ApiResponseObject<ListPriceEntity> save(ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        if(this.listPriceRepository.existsById(new ListPricePK(idcompany,listPriceEntity.getCodlistprice()))){
            throw new GenericObjectServiceException(ID_EXISTS(listPriceEntity.getCodlistprice().toString()));
        }
        listPriceEntity.setIdcompany(idcompany);
        return new ApiResponseObject<ListPriceEntity>(Optional.of(this.listPriceRepository.save(listPriceEntity)));
    }

    @Override
    public ApiResponseObject<ListPriceEntity> update(ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        if(!this.listPriceRepository.existsById(new ListPricePK(idcompany,listPriceEntity.getCodlistprice()))){
            throw new GenericObjectServiceException(ID_NOT_EXISTS(listPriceEntity.getCodlistprice().toString()));
        }
        listPriceEntity.setIdcompany(idcompany);
        return new ApiResponseObject<ListPriceEntity>(Optional.of(this.listPriceRepository.save(listPriceEntity)));
    }

    @Override
    public ApiResponseObject<ListPriceEntity> delete(ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        this.listPriceRepository.deleteById(new ListPricePK(idcompany,listPriceEntity.getCodlistprice()));
        return new ApiResponseObject<ListPriceEntity>(Optional.empty());
    }

    @Override
    public ApiResponseObject<ListPriceEntity> undelete(ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        throw new GenericObjectServiceException(405);
    }
}
