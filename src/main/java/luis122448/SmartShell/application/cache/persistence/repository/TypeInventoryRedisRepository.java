package luis122448.SmartShell.application.cache.persistence.repository;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.cache.persistence.entity.TypeInventoryRedis;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TypeInventoryRedisRepository {

    public static final String TYPE_INVENTORY = "TYPE_INVENTORY";

    private HashOperations hashOperations;// PreparedStatemnt

    private RedisTemplate redisTemplate;  // Connection

    public TypeInventoryRedisRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash(); //K,V
    }

    @SuppressWarnings("unchecked")
    public void save(TypeInventoryRedis typeInventoryRedis) {
        log.info("save...");
        hashOperations.put(TYPE_INVENTORY, typeInventoryRedis.getTypinv(), typeInventoryRedis);
    }

    @SuppressWarnings("unchecked")
    public List<TypeInventoryRedis> findAll() {
        log.info("findAll...");
        return hashOperations.values(TYPE_INVENTORY);
    }

    @SuppressWarnings("unchecked")
    public TypeInventoryRedis findById(Integer typinv) {
        log.info("findById...");
        return (TypeInventoryRedis) hashOperations.get(TYPE_INVENTORY, typinv);
    }


    public void update(TypeInventoryRedis typeInventoryRedis) {
        log.info("update...");
        save(typeInventoryRedis);
    }

    @SuppressWarnings("unchecked")
    public void delete(Integer typinv) {
        log.info("delete...");
        hashOperations.delete(TYPE_INVENTORY, typinv);
    }

    public void deleteAll() {
        log.info("delete all...");
        hashOperations
                .entries(TYPE_INVENTORY).keySet().forEach(haskKey -> hashOperations.delete(TYPE_INVENTORY,haskKey));
    }
}
