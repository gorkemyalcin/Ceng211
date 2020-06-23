package exceptionHandlers;

public class PatientNotFoundException extends Exception {

	private String message;

	public PatientNotFoundException(String message) {
		this.setMessage(message);
	}

	public PatientNotFoundException() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8293579092370899781L;

}
