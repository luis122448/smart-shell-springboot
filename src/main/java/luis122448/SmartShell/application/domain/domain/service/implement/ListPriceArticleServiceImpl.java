package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.application.domain.domain.model.ListPriceArticleDTO;
import luis122448.SmartShell.application.domain.persistence.mapper.ListPriceArticleMapper;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ListPriceArticleService;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceArticleEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ListPriceArticlePK;
import luis122448.SmartShell.application.domain.persistence.repository.ListPriceArticleRepository;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static luis122448.SmartShell.util.code.Utils.copyNonNullProperties;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ListPriceArticleServiceImpl implements ListPriceArticleService{
    private final ListPriceArticleRepository listPriceArticleRepository;
    private final ListPriceArticleMapper listPriceArticleMapper;
    private final SecurityContextInitializer securityContextInitializer;
    public ListPriceArticleServiceImpl(ListPriceArticleRepository listPriceArticleRepository, ListPriceArticleMapper listPriceArticleMapper, SecurityContextInitializer securityContextInitializer) {
        this.listPriceArticleRepository = listPriceArticleRepository;
        this.listPriceArticleMapper = listPriceArticleMapper;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponsePage<ListPriceArticleEntity> findByPage(ListPriceArticleDTO dto, Pageable pageable) throws GenericPageServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        Page<ListPriceArticleEntity> page = this.listPriceArticleRepository.findByLike(idcompany,dto.getCodlistprice(),dto.getCodart(),dto.getDesart(),pageable);
        if (page.getContent().isEmpty()) {
            throw new GenericPageServiceException(404);
        }
        return new ApiResponsePage<>(Optional.of(page));
    }

    @Override
    public ApiResponseList<ListPriceArticleEntity> findByCodlistprice(Integer codlistprice, String status) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<ListPriceArticleEntity> listEntity = this.listPriceArticleRepository.findByIdcompanyAndCodlistprice(idcompany,codlistprice);
        if (listEntity.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(listEntity));
    }

    @Override
    public ApiResponseList<ListPriceArticleEntity> findByCodart(String codart, String status) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        List<ListPriceArticleEntity> listEntity = this.listPriceArticleRepository.findByIdcompanyAndCodart(idcompany,codart);
        if (listEntity.isEmpty()) {
            throw new GenericListServiceException(404);
        }
        return new ApiResponseList<>(Optional.of(listEntity));
    }

    @Override
    public ApiResponseObject<ListPriceArticleEntity> findById(ListPriceArticleDTO dto) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        ListPriceArticlePK id = new ListPriceArticlePK(idcompany,dto.getCodlistprice(),dto.getCodart());
        Optional<ListPriceArticleEntity> searchEntity = this.listPriceArticleRepository.findById(id);
        if (!this.listPriceArticleRepository.existsById(id)){
            throw new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()));
        }
        return new ApiResponseObject<>(searchEntity);
    }

    @Override
    public ApiResponseObject<ListPriceArticleEntity> save(ListPriceArticleDTO dto) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        String codUser = securityContextInitializer.getCodUser();
        ListPriceArticlePK id = new ListPriceArticlePK(idcompany,dto.getCodlistprice(),dto.getCodart());
        if (this.listPriceArticleRepository.existsById(id)){
           throw new GenericObjectServiceException(ID_EXISTS(id.toString()));
        }
        ListPriceArticleEntity newEntity = this.listPriceArticleMapper.toListPriceArticleEntity(dto);
        newEntity.setIdcompany(idcompany);
        newEntity.setCreateby(codUser);
        newEntity.setCreateat(LocalDateTime.now());
        newEntity = calculateFields(newEntity);
        ListPriceArticleEntity savedEntity = this.listPriceArticleRepository.save(newEntity);
        return new ApiResponseObject<>(Optional.of(savedEntity));
    }

    @Override
    public ApiResponseObject<ListPriceArticleEntity> update(ListPriceArticleDTO dto) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        String codUser = securityContextInitializer.getCodUser();
        ListPriceArticlePK id = new ListPriceArticlePK(idcompany,dto.getCodlistprice(),dto.getCodart());
        ListPriceArticleEntity existingEntity = this.listPriceArticleRepository.findById(id).orElseThrow(
                () -> new GenericObjectServiceException(ID_NOT_EXISTS(id.toString()))
        );
        ListPriceArticleEntity updateEntity = this.listPriceArticleMapper.toListPriceArticleEntity(dto);
        updateEntity.setIdcompany(idcompany);
        updateEntity.setUpdateby(codUser);
        updateEntity.setUpdateat(LocalDateTime.now());
        updateEntity = calculateFields(updateEntity);
        copyNonNullProperties(existingEntity, updateEntity);
        ListPriceArticleEntity savedEntity = this.listPriceArticleRepository.save(updateEntity);
        return new ApiResponseObject<>(Optional.of(savedEntity));
    }

    @Override
    public ApiResponseObject<ListPriceArticleEntity> delete(ListPriceArticleDTO dto) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        ListPriceArticlePK id = new ListPriceArticlePK(idcompany,dto.getCodlistprice(),dto.getCodart());
        this.listPriceArticleRepository.deleteById(id);
        return new ApiResponseObject<>(Optional.empty());
    }

    public ListPriceArticleEntity calculateFields(ListPriceArticleEntity listPriceArticleEntity){
        listPriceArticleEntity.setImplistprice(listPriceArticleEntity.getPrice());
        BigDecimal totalDiscount = listPriceArticleEntity.getDesc01().add(listPriceArticleEntity.getDesc02()).add(listPriceArticleEntity.getDesc03()).add(listPriceArticleEntity.getDesc04());
        if (totalDiscount.compareTo(listPriceArticleEntity.getDesmax()) > 0){
            String.format("Total discount (%s) is greater than maximum allowed discount (%s).",
                    totalDiscount, listPriceArticleEntity.getDesmax());
        }
        listPriceArticleEntity.setImpigv(BigDecimal.ZERO);
        listPriceArticleEntity.setImpisc(BigDecimal.ZERO);
        listPriceArticleEntity.setImptribadd01(BigDecimal.ZERO);
        listPriceArticleEntity.setImptribadd02(BigDecimal.ZERO);
        listPriceArticleEntity.setImptribadd03(BigDecimal.ZERO);
        listPriceArticleEntity.setImptribadd04(BigDecimal.ZERO);
        listPriceArticleEntity.setImpdesctotal(listPriceArticleEntity.getPrice().multiply(totalDiscount));
        listPriceArticleEntity.setImpsaleprice(listPriceArticleEntity.getPrice().subtract(listPriceArticleEntity.getImpdesctotal()));
        listPriceArticleEntity.setImptribtotal(BigDecimal.ZERO);
        listPriceArticleEntity.setImptotal(listPriceArticleEntity.getImpsaleprice().subtract(listPriceArticleEntity.getImptribtotal()));
        return listPriceArticleEntity;
    }
}
