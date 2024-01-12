package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.application.domain.persistence.entity.ListPriceArticleEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.ListPriceArticlePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListPriceArticleRepository extends JpaRepository<ListPriceArticleEntity, ListPriceArticlePK> {

    List<ListPriceArticleEntity> findByCodlistprice(@Param(value = "codlistprice") Integer codlistprice) throws GenericListServiceException;

    List<ListPriceArticleEntity> findByCodart(@Param(value = "codart") String codart) throws GenericListServiceException;

    Page<ListPriceArticleEntity> findByCodlistpriceAndCodartLikeAndDesartLikeOrderByCodartAsc(@Param(value = "codlistprice") Integer codlistprice,
                                                                                              @Param(value = "codart") String codart,
                                                                                              @Param(value = "desart") String desart,
                                                                                              Pageable pageable) throws GenericPageServiceException;

}
