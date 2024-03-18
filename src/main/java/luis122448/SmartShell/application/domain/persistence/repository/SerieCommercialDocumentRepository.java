package luis122448.SmartShell.application.domain.persistence.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import luis122448.SmartShell.application.domain.persistence.entity.SerieCommercialDocumentEntity;
import luis122448.SmartShell.application.domain.persistence.entity.primary.SerieCommercialDocumentPK;

public interface SerieCommercialDocumentRepository extends JpaRepository<SerieCommercialDocumentEntity, SerieCommercialDocumentPK> {
    
	@Query("SELECT sdc FROM SerieCommercialDocumentEntity sdc WHERE sdc.idcompany = :idcompany AND sdc.typcomdoc = :typcomdoc AND sdc.status ='Y'")
    List<SerieCommercialDocumentEntity> findByIdcompanyAndTypcomdoc(@Param("idcompany") Integer idcompany, @Param("typcomdoc") Integer typcomdoc);

    List<SerieCommercialDocumentEntity> findByIdcompany(Integer idcompany);

}
