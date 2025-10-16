package luis122448.SmartShell.application.domain.persistence.repository.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProcedureException extends Exception {

	private Integer status;
	private String message;
	private String logMessage;
	public GenericProcedureException() {
		super();
		this.status = 400;
		this.message = message;
		this.logMessage = message;
	}

	public GenericProcedureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GenericProcedureException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GenericProcedureException(String message) {
		super(message);
		this.status = 400;
		this.message = message;
		this.logMessage = message;
	}

	public GenericProcedureException(String message ,String logMessage) {
		super(message);
		this.status = 400;
		this.message = message;
		this.logMessage = logMessage;
	}

	public GenericProcedureException(Throwable cause) {
		super(cause);
		this.status = 400;
	}

	public GenericProcedureException(String message ,String logMessage, Throwable cause) {
		super(message, cause);
		this.status = 400;
		this.message = message;
		this.logMessage = logMessage;
	}


}
