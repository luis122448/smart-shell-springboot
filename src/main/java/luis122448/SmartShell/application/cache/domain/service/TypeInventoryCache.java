package luis122448.SmartShell.application.cache.domain.service;

import luis122448.SmartShell.application.cache.persistence.entity.TypeInventoryRedis;
import luis122448.SmartShell.application.cache.persistence.repository.TypeInventoryRedisRepository;
import luis122448.SmartShell.application.domain.persistence.entity.TypeInventoryEntity;
import luis122448.SmartShell.application.domain.persistence.repository.TypeInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeInventoryCache {

    private final TypeInventoryRepository typeInventoryRepository;
    private final TypeInventoryRedisRepository typeInventoryRedisRepository;
    public TypeInventoryCache(TypeInventoryRepository typeInventoryRepository, TypeInventoryRedisRepository typeInventoryRedisRepository) {
        this.typeInventoryRepository = typeInventoryRepository;
        this.typeInventoryRedisRepository = typeInventoryRedisRepository;
    }

    public void UpdateCache(){
        this.typeInventoryRedisRepository.deleteAll();
        List<TypeInventoryEntity> typeInventoryEntityList = this.typeInventoryRepository.findAll();
        for (TypeInventoryEntity typeInventoryEntity : typeInventoryEntityList) {
            TypeInventoryRedis typeInventoryRedis = TypeInventoryRedis.builder()
                    .typinv(typeInventoryEntity.getTypinv())
                    .abrevi(typeInventoryEntity.getAbrevi())
                    .descri(typeInventoryEntity.getDescri())
                    .commen(typeInventoryEntity.getCommen())
                    .defaul(typeInventoryEntity.getDefaul())
                    .build();
            this.typeInventoryRedisRepository.save(typeInventoryRedis);
        }
    }
}
