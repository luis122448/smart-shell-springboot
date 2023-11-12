package luis122448.SmartShell.application.cache.components;

import lombok.extern.slf4j.Slf4j;
import luis122448.SmartShell.application.cache.domain.service.TypeInventoryCache;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ComponentInit implements ApplicationRunner {

    private final TypeInventoryCache typeInventoryCache;
    public ComponentInit(TypeInventoryCache typeInventoryCache) {
        this.typeInventoryCache = typeInventoryCache;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Go, Init");
        this.typeInventoryCache.UpdateCache();
    }
}
