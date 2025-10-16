package luis122448.SmartShell.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericObjectServiceException extends Exception {
	private Integer status;
	private String message;
	private String logMessage;
	public GenericObjectServiceException() {
		super();
		this.status = 400;
		this.message = "AN UNKNOWN ERROR HAS OCCURRED";
		this.logMessage = "AN UNKNOWN ERROR HAS OCCURRED";
	}
	public GenericObjectServiceException(int status) {
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
	public GenericObjectServiceException(String message) {
		super(message);
		this.status = 400;
		this.message = message;
		this.logMessage = message;
	}
	public GenericObjectServiceException(int status,String message) {
		super(message);
		this.status = status;
		this.message = message;
		this.logMessage = message;
	}
	public GenericObjectServiceException(String message, String logMessage) {
		super(message);
		this.status = 400;
		this.message = message;
		this.logMessage = logMessage;
	}
	public GenericObjectServiceException(String message, Throwable cause) {
		super(message, cause);
		this.status = 400;
		this.message = message;
		if (cause.getCause() != null){
			this.logMessage = cause.getCause().getMessage();
		}
	}
	public GenericObjectServiceException(String message, String logMessage, Throwable cause) {
		super(message, cause);
		this.status = 400;
		this.message = message;
		this.logMessage = logMessage;
	}
	public GenericObjectServiceException(Throwable cause) {
		super(cause);
		this.status = 400;
		this.message = cause.getMessage();
		if (cause.getCause() != null){
			this.logMessage = cause.getCause().getMessage();
		}
	}
	protected GenericObjectServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.status = 400;
		this.message = message;
		this.logMessage = cause.getMessage();
	}

}
