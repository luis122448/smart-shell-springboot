package luis122448.SmartShell.application.domain.persistence.repository.exception;

public class GenericFunctionException extends Exception{
    public GenericFunctionException() {
        super();
    }

    public GenericFunctionException(String message) {
        super(message);
    }

    public GenericFunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericFunctionException(Throwable cause) {
        super(cause);
    }

    protected GenericFunctionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
