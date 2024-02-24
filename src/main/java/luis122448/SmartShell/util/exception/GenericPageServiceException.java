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
        this.status = 200;
        this.message = "Ok";
        this.logMessage = message;
    }
    public GenericPageServiceException(int status) {
        super();
        this.status = status;
        if (status == 1) {
            this.message = "Ok";
        } else if (status == 404) {
            this.message = "No se encontraron registros";
        } else {
            this.message = "Error desconocido";
        }
        this.logMessage = message;
    }
    public GenericPageServiceException(String message) {
        super(message);
        this.status = 200;
        this.logMessage = message;
    }
    public GenericPageServiceException(String message , String logMessage) {
        super(message);
        this.status = 200;
        this.logMessage = logMessage;
    }

    public GenericPageServiceException(String message, Throwable cause) {
        super(message, cause);
        this.status = 200;
        this.logMessage = message;
    }
    public GenericPageServiceException(String message , String logMessage, Throwable cause) {
        super(message, cause);
        this.status = 200;
        this.logMessage = logMessage;
    }

    public GenericPageServiceException(Throwable cause) {
        super(cause);
    }

    protected GenericPageServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
