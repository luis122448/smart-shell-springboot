package luis122448.SmartShell.security.application.utility;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class ApiResponseMetadata<T,Q> {

    private Short status;
    private String message;
    private Optional<T> object;
    private Optional<Q> metadata;

    public ApiResponseMetadata(int status, String message, Optional<T> object, Optional<Q> metadata) {
        this.status = (short) status;
        this.message = message;
        this.object = object;
        this.metadata = metadata;
    }

    public ApiResponseMetadata(Optional<T> object, Optional<Q> metadata) {
        this.status = 1;
        this.message = "Success";
        this.object = object;
        this.metadata = metadata;
    }

    public ApiResponseMetadata(int status, String message) {
        this.status = (short) status;
        this.message = message;
        this.object = Optional.empty();
        this.metadata = Optional.empty();
    }

}
