package luis122448.SmartShell.application.domain.persistence.repository.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProcedureException extends Exception {

	private String logMessage;
	public GenericProcedureException() {
		super();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated constructor stub
	}

	public GenericProcedureException(String message ,String logMessage) {
		super(message);
		this.logMessage = logMessage;
	}

	public GenericProcedureException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public GenericProcedureException(String message ,String logMessage, Throwable cause) {
		super(message, cause);
		this.logMessage = logMessage;
	}


}
