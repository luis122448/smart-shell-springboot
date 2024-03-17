package luis122448.SmartShell.application.domain.domain.service.implement;

import luis122448.SmartShell.application.domain.domain.component.SecurityContextInitializer;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.application.domain.domain.service.service.ListPriceArticleService;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceArticleEntity;
import luis122448.SmartShell.application.domain.persistence.entity.key.ListPriceArticlePK;
import luis122448.SmartShell.application.domain.persistence.repository.ListPriceArticleRepository;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static luis122448.SmartShell.util.constant.MESSAGEConstants.*;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ListPriceArticleServiceImpl implements ListPriceArticleService{
    private final ListPriceArticleRepository listPriceArticleRepository;
    private final SecurityContextInitializer securityContextInitializer;
    public ListPriceArticleServiceImpl(ListPriceArticleRepository listPriceArticleRepository, SecurityContextInitializer securityContextInitializer) {
        this.listPriceArticleRepository = listPriceArticleRepository;
        this.securityContextInitializer = securityContextInitializer;
    }

    @Override
    public ApiResponsePage<ListPriceArticleEntity> findByPage(ListPriceArticleEntity listPriceArticleEntity, Pageable pageable) throws GenericPageServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        Page<ListPriceArticleEntity> page = this.listPriceArticleRepository.findByLike(idcompany,listPriceArticleEntity.getCodlistprice(),listPriceArticleEntity.getCodart(),listPriceArticleEntity.getDesart(),pageable);
        if (page.getContent().isEmpty()) {
            throw new GenericPageServiceException(404);
        }
        return new ApiResponsePage<>(Optional.of(page));
    }

    @Override
    public ApiResponseList<ListPriceArticleEntity> findByCodlistprice(ListPriceArticleEntity listPriceArticleEntity) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        return new ApiResponseList<>(Optional.of(this.listPriceArticleRepository.findByIdcompanyAndCodlistprice(idcompany,listPriceArticleEntity.getCodlistprice())));
    }

    @Override
    public ApiResponseList<ListPriceArticleEntity> findByLike(ListPriceArticleEntity listPriceArticleEntity) throws GenericListServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        return new ApiResponseList<>(Optional.of(this.listPriceArticleRepository.findByIdcompanyAndCodart(idcompany,listPriceArticleEntity.getCodart())));
    }

    @Override
    public ApiResponseObject<ListPriceArticleEntity> findById(ListPriceArticleEntity listPriceArticleEntity) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        ListPriceArticlePK tmp = new ListPriceArticlePK(idcompany,listPriceArticleEntity.getCodlistprice(),listPriceArticleEntity.getCodart());
        return new ApiResponseObject<>(this.listPriceArticleRepository.findById(tmp));
    }

    @Override
    public ApiResponseObject<ListPriceArticleEntity> save(ListPriceArticleEntity listPriceArticleEntity) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        ListPriceArticlePK pk = new ListPriceArticlePK(idcompany,listPriceArticleEntity.getCodlistprice(),listPriceArticleEntity.getCodart());
        if (this.listPriceArticleRepository.existsById(pk)){
           throw new GenericObjectServiceException(ID_EXISTS(pk.getClass().toString()));
        }
        listPriceArticleEntity.setImplistprice(listPriceArticleEntity.getPrice());
        BigDecimal totalDiscount = listPriceArticleEntity.getDesc01().add(listPriceArticleEntity.getDesc02()).add(listPriceArticleEntity.getDesc03()).add(listPriceArticleEntity.getDesc04());
        if (totalDiscount.compareTo(listPriceArticleEntity.getDesmax()) > 0){
            String.format("Total discount (%s) is greater than maximum allowed discount (%s).",
                    totalDiscount, listPriceArticleEntity.getDesmax());
        }
        return new ApiResponseObject<ListPriceArticleEntity>(Optional.of(this.listPriceArticleRepository.save(calculateFields(listPriceArticleEntity))));
    }

    @Override
    public ApiResponseObject<ListPriceArticleEntity> update(ListPriceArticleEntity listPriceArticleEntity) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        ListPriceArticlePK tmp = new ListPriceArticlePK(idcompany,listPriceArticleEntity.getCodlistprice(),listPriceArticleEntity.getCodart());
        if (!this.listPriceArticleRepository.existsById(tmp)){
            throw new GenericObjectServiceException(ID_NOT_EXISTS(tmp.getClass().toString()));
        }
        listPriceArticleEntity.setImplistprice(listPriceArticleEntity.getPrice());
        BigDecimal totalDiscount = listPriceArticleEntity.getDesc01().add(listPriceArticleEntity.getDesc02()).add(listPriceArticleEntity.getDesc03()).add(listPriceArticleEntity.getDesc04());
        if (totalDiscount.compareTo(listPriceArticleEntity.getDesmax()) > 0){
            String.format("Total discount (%s) is greater than maximum allowed discount (%s).",
                    totalDiscount, listPriceArticleEntity.getDesmax());
        }
        return new ApiResponseObject<ListPriceArticleEntity>(Optional.of(this.listPriceArticleRepository.save(calculateFields(listPriceArticleEntity))));
    }

    @Override
    public ApiResponseObject<ListPriceArticleEntity> delete(ListPriceArticleEntity listPriceArticleEntity) throws GenericObjectServiceException {
        Integer idcompany = securityContextInitializer.getIdCompany();
        ListPriceArticlePK tmp = new ListPriceArticlePK(idcompany,listPriceArticleEntity.getCodlistprice(),listPriceArticleEntity.getCodart());
        this.listPriceArticleRepository.deleteById(tmp);
        return new ApiResponseObject<ListPriceArticleEntity>(Optional.empty());
    }

    @Override
    public ApiResponseObject<ListPriceArticleEntity> undelete(ListPriceArticleEntity listPriceArticleEntity) throws GenericObjectServiceException {
        throw new GenericObjectServiceException(405);
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
