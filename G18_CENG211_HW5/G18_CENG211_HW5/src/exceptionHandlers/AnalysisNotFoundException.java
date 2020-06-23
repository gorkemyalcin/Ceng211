package exceptionHandlers;

public class AnalysisNotFoundException extends Exception {

	private String message;

	public AnalysisNotFoundException(String message) {
		this.setMessage(message);
	}

	public AnalysisNotFoundException() {
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
