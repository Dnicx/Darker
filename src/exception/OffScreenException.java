package exception;

public class OffScreenException extends Exception {

	public OffScreenException() {
		super();
	}

	public OffScreenException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "position fall off logical plain";
	}

}
