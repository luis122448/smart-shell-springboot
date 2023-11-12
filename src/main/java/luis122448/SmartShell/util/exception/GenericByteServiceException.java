package luis122448.SmartShell.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericByteServiceException extends Exception{
    private String logMessage;
    public GenericByteServiceException() {
        super();
    }

    public GenericByteServiceException(String message) {
        super(message);
        this.logMessage = message;
    }
    public GenericByteServiceException(String message , String logMessage) {
        super(message);
        this.logMessage = logMessage;
    }

    public GenericByteServiceException(String message, Throwable cause) {
        super(message, cause);
        this.logMessage = message;
    }
    public GenericByteServiceException(String message , String logMessage, Throwable cause) {
        super(message, cause);
        this.logMessage = logMessage;
    }

    public GenericByteServiceException(Throwable cause) {
        super(cause);
    }

    protected GenericByteServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
