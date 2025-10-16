package luis122448.SmartShell.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericPageServiceException extends Exception{
    private Integer status;
    private String message;
    private String logMessage;
    public GenericPageServiceException() {
        super();
        this.status = 400;
        this.message = "AN UNKNOWN ERROR HAS OCCURRED";
        this.logMessage = "AN UNKNOWN ERROR HAS OCCURRED";
    }
    public GenericPageServiceException(int status) {
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
    public GenericPageServiceException(String message) {
        super(message);
        this.status = 400;
        this.message = message;
        this.logMessage = message;
    }
    public GenericPageServiceException(int status,String message) {
        super(message);
        this.status = status;
        this.message = message;
        this.logMessage = message;
    }
    public GenericPageServiceException(String message , String logMessage) {
        super(message);
        this.status = 400;
        this.message = message;
        this.logMessage = logMessage;
    }
    public GenericPageServiceException(String message, Throwable cause) {
        super(message, cause);
        this.status = 400;
        this.message = message;
        if (cause.getCause() != null){
            this.logMessage = cause.getCause().getMessage();
        }
    }
    public GenericPageServiceException(String message , String logMessage, Throwable cause) {
        super(message, cause);
        this.status = 400;
        this.message = message;
        this.logMessage = logMessage;
    }
    public GenericPageServiceException(Throwable cause) {
        super(cause);
        this.status = 400;
        this.message = cause.getMessage();
        if (cause.getCause() != null){
            this.logMessage = cause.getCause().getMessage();
        }
    }
    protected GenericPageServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = 400;
        this.message = message;
        this.logMessage = cause.getMessage();
    }

}
