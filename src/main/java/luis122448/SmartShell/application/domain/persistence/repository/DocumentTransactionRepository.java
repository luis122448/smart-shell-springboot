package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.DocumentTransactionEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.DocumentTransactionPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentTransactionRepository extends JpaRepository<DocumentTransactionEntity, DocumentTransactionPK> {

    List<DocumentTransactionEntity> findByIdcompanyAndNumint(Integer idcompany, Long numint);
}
