package luis122448.SmartShell.application.cache.persistence.entity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
@RedisHash("TypeInventory")
public class TypeInventoryRedis implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer typinv;
    private String abrevi;
    private String descri;
    private String codext;
    private String observ;
    private String commen;
    private String defaul;
}
