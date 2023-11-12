package luis122448.SmartShell.application.archive.persistence.repository;

import luis122448.SmartShell.application.archive.persistence.entity.ArchiveEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArchiveRepository extends MongoRepository<ArchiveEntity, String> {
}
