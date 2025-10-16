package luis122448.SmartShell.security.application.service.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericAuthServiceException extends Exception {
    private Integer status;
    private String message;
    private String logMessage;
    public GenericAuthServiceException() {
        super();
        this.status = 401;
        this.message = "AN UNKNOWN ERROR HAS OCCURRED";
        this.logMessage = "AN UNKNOWN ERROR HAS OCCURRED";
    }
    public GenericAuthServiceException(int status) {
        super();
        this.status = status;
        if (status == 1) {
            this.message = "SUCCESS";
        } else if (status == 404) {
            this.message = "NO RECORDS FOUND";
        } else {
            this.message = "AN UNKNOWN ERROR HAS OCCURRED";
        }
        this.logMessage = message;
    }
    public GenericAuthServiceException(String message) {
        super(message);
        this.status = 401;
        this.message = message;
        this.logMessage = message;
    }
    public GenericAuthServiceException(int status,String message) {
        super(message);
        this.status = status;
        this.message = message;
        this.logMessage = message;
    }
    public GenericAuthServiceException(String message , String logMessage) {
        super(message);
        this.status = 401;
        this.message = message;
        this.logMessage = logMessage;
    }
    public GenericAuthServiceException(String message, Throwable cause) {
        super(message, cause);
        this.status = 401;
        this.message = message;
        if (cause.getCause() != null){
            this.logMessage = cause.getCause().getMessage();
        }
    }
    public GenericAuthServiceException(String message , String logMessage, Throwable cause) {
        super(message, cause);
        this.status = 401;
        this.message = message;
        this.logMessage = logMessage;
    }
    public GenericAuthServiceException(Throwable cause) {
        super(cause);
        this.status = 401;
        this.message = cause.getMessage();
        if (cause.getCause() != null){
            this.logMessage = cause.getCause().getMessage();
        }
    }
    protected GenericAuthServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = 401;
        this.message = message;
        this.logMessage = cause.getMessage();
    }

}
