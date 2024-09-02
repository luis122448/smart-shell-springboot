package luis122448.SmartShell.util.object.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
@NoArgsConstructor
public class ApiResponseObject<T> {

	private Short status;
    private String message;
    private Optional<T> object;
    // Log
    private String logMessage;
    private String logUser;
    private LocalDateTime logTime;

    public ApiResponseObject(Optional<T> object) {
        this.status = (short) 1;
        this.message = "OK";
        this.object = object;
        this.logMessage = message;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            this.logUser = authentication.getName();
        } else {
            this.logUser = "Unknown";
        }
        this.logTime = LocalDateTime.now();
    }

    public ApiResponseObject(int status, String message, Optional<T> object) {
        this.status = (short) status;
        this.message = message;
        this.object = object;
        this.logMessage = message;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            this.logUser = authentication.getName();
        } else {
            this.logUser = "Unknown";
        }
        this.logTime = LocalDateTime.now();
    }

    public ApiResponseObject(int status, String message, String logMessage, Optional<T> object) {
        this.status = (short) status;
        this.message = message;
        this.object = object;
        this.logMessage = logMessage;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            this.logUser = authentication.getName();
        } else {
            this.logUser = "Unknown";
        }
        this.logTime = LocalDateTime.now();
    }

}
