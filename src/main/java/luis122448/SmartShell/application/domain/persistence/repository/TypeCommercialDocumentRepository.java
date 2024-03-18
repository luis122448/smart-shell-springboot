package luis122448.SmartShell.application.domain.persistence.repository;

import luis122448.SmartShell.application.domain.persistence.entity.primary.TypeCommercialDocumentPk;
import org.springframework.data.jpa.repository.JpaRepository;

import luis122448.SmartShell.application.domain.persistence.entity.TypeCommercialDocumentEntity;

import java.util.List;

public interface TypeCommercialDocumentRepository extends JpaRepository<TypeCommercialDocumentEntity, TypeCommercialDocumentPk> {
    List<TypeCommercialDocumentEntity> findByIdcompany(Integer idcompany);
}
