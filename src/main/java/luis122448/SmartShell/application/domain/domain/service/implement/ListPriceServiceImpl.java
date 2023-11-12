package luis122448.SmartShell.application.domain.domain.service.implement;

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
    public ListPriceServiceImpl(ListPriceRepository listPriceRepository) {
        this.listPriceRepository = listPriceRepository;
    }

    @Override
    public ApiResponseList<ListPriceEntity> findAll(ListPriceEntity listPriceEntity) throws GenericListServiceException {
        return new ApiResponseList<ListPriceEntity>(1,"Ok", Optional.of(this.listPriceRepository.findAll()));
    }

    @Override
    public ApiResponseList<ListPriceEntity> findByLike(ListPriceEntity listPriceEntity) throws GenericListServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<ListPriceEntity> findById(ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        return new ApiResponseObject<ListPriceEntity>(1,"Ok", this.listPriceRepository.findById(listPriceEntity.getCodlistprice()));
    }

    @Override
    public ApiResponseObject<ListPriceEntity> save(ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        if(this.listPriceRepository.existsById(listPriceEntity.getCodlistprice())){
            throw new GenericObjectServiceException(ID_EXISTS(listPriceEntity.getCodlistprice().toString()));
        }
        return new ApiResponseObject<ListPriceEntity>(1,"Ok",Optional.of(this.listPriceRepository.save(listPriceEntity)));
    }

    @Override
    public ApiResponseObject<ListPriceEntity> update(ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        if(!this.listPriceRepository.existsById(listPriceEntity.getCodlistprice())){
            throw new GenericObjectServiceException(ID_NOT_EXISTS(listPriceEntity.getCodlistprice().toString()));
        }
        return new ApiResponseObject<ListPriceEntity>(1,"Ok",Optional.of(this.listPriceRepository.save(listPriceEntity)));
    }

    @Override
    public ApiResponseObject<ListPriceEntity> delete(ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        this.listPriceRepository.deleteById(listPriceEntity.getCodlistprice());
        return new ApiResponseObject<ListPriceEntity>(1,"Ok",Optional.empty());
    }

    @Override
    public ApiResponseObject<ListPriceEntity> undelete(ListPriceEntity listPriceEntity) throws GenericObjectServiceException {
        return null;
    }
}
