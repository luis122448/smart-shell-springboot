package luis122448.SmartShell.application.cache.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import luis122448.SmartShell.application.cache.domain.service.TypeInventoryCache;
import luis122448.SmartShell.application.cache.persistence.entity.TypeInventoryRedis;
import luis122448.SmartShell.application.cache.persistence.repository.TypeInventoryRedisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static luis122448.SmartShell.application.domain.web.constant.APIConstants.*;

@RestController
@RequestMapping(PATH_TEST)
public class TestControllerCache {

    private final TypeInventoryRedisRepository typeInventoryRedisRepository;
    private final TypeInventoryCache typeInventoryCache;

    public TestControllerCache(TypeInventoryRedisRepository typeInventoryRedisRepository, TypeInventoryCache typeInventoryCache) {
        this.typeInventoryRedisRepository = typeInventoryRedisRepository;
        this.typeInventoryCache = typeInventoryCache;
    }

    @Operation(tags = {TAG_TEST})
    @GetMapping("/by-all")
    public ResponseEntity<?> findByAll(){
        List<TypeInventoryRedis> typeInventoryRedis = this.typeInventoryRedisRepository.findAll();
        return ResponseEntity.ok(typeInventoryRedis);
    }

    @Operation(tags = {TAG_TEST})
    @PostMapping
    public ResponseEntity<?> findByTest(){
        typeInventoryCache.UpdateCache();
        return ResponseEntity.ok("Ok");
    }

    @Operation(tags = {TAG_TEST})
    @DeleteMapping
    public ResponseEntity<?> delete(){
        this.typeInventoryRedisRepository.deleteAll();
        return ResponseEntity.ok("Ok");
    }

}
