package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.ListPriceDTO;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ListPricePK;
import luis122448.SmartShell.application.domain.persistence.mapper.ListPriceMapper;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ListPriceService;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceEntity;
import luis122448.SmartShell.application.domain.persistence.repository.ListPriceRepository;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import static luis122448.SmartShell.util.code.Utils.copyNonNullProperties;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ListPriceServiceImpl implements ListPriceService {
    private final ListPriceRepository listPriceRepository;
    private final ListPriceMapper listPriceMapper;
    private final SecurityContextInitializer securityContextInitializer;
    public ListPriceServiceImpl(ListPriceRepository listPriceRepository, ListPriceMapper listPriceMapper, SecurityContextInitializer securityContextInitializer) {
        this.listPriceRepository = listPriceRepository;
        this.listPriceMapper = listPriceMapper;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponseList<ListPriceEntity> findAll(String status) throws GenericListServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        List<ListPriceEntity> listEntity = this.listPriceRepository.findByIdcompany(idCompany, status);
        if (listEntity.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(listEntity));
    }

    @Override
    public ApiResponseObject<ListPriceEntity> findById(ListPriceDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        ListPricePK id = new ListPricePK(idCompany, dto.getCodlistprice());
        Optional<ListPriceEntity> searchEntity = this.listPriceRepository.findById(id);
        if (searchEntity.isEmpty()) {
            throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
        }
        return new ApiResponseObject<>(searchEntity);
    }

    @Override
    public ApiResponseObject<ListPriceEntity> save(ListPriceDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        String codUser = securityContextInitializer.getCodUser();
        ListPricePK id = new ListPricePK(idCompany, dto.getCodlistprice());
        if (this.listPriceRepository.existsById(id)) {
            throw new GenericObjectServiceException(ID_EXISTS(id.toString()));
        }
        ListPriceEntity newEntity = this.listPriceMapper.toListPriceEntity(dto);
        newEntity.setIdcompany(idCompany);
        newEntity.setCreateby(codUser);
        newEntity.setCreateat(LocalDateTime.now());
        ListPriceEntity savedEntity = this.listPriceRepository.save(newEntity);
        return new ApiResponseObject<>(Optional.of(savedEntity));
    }

    @Override
    public ApiResponseObject<ListPriceEntity> update(ListPriceDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        String codUser = securityContextInitializer.getCodUser();
        ListPricePK id = new ListPricePK(idCompany, dto.getCodlistprice());
        Optional<ListPriceEntity> searchEntity = this.listPriceRepository.findById(id);
        if (searchEntity.isEmpty()) {
            throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
        }
        ListPriceEntity newEntity = this.listPriceMapper.toListPriceEntity(dto);
        newEntity.setIdcompany(idCompany);
        newEntity.setUpdateby(codUser);
        newEntity.setUpdateat(LocalDateTime.now());
        copyNonNullProperties(searchEntity.get(), newEntity);
        ListPriceEntity savedEntity = this.listPriceRepository.save(newEntity);
        return new ApiResponseObject<>(Optional.of(savedEntity));
    }

    @Override
    public ApiResponseObject<ListPriceEntity> delete(ListPriceDTO dto) throws GenericObjectServiceException {
        Integer idCompany = securityContextInitializer.getIdCompany();
        ListPricePK id = new ListPricePK(idCompany, dto.getCodlistprice());
        this.listPriceRepository.deleteById(id);
        return new ApiResponseObject<>(Optional.empty());
    }

}
