package exception;

public class ResourceException extends Exception {

	public ResourceException() {
		super();
	}

	public ResourceException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "error load resource";
	}
}
