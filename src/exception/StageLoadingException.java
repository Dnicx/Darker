package exception;

public class StageLoadingException extends Exception {
	
	public static final int COLLIDEBOX_NOTFOUND = 0;
	public static final int MOB_NOTFOUND = 1;
	public static final int WRONG_FORMAT = 2;
	
	private int errorType;
	private String message;
	
	public StageLoadingException(int type) {
		this(type, "");
	}
	
	public StageLoadingException(int type, String message) {
		this.errorType = type;
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		if(errorType == COLLIDEBOX_NOTFOUND) return "Can not Find CollideBox.txt " + message;
		if(errorType == MOB_NOTFOUND) return "Can not find mob.txt" + message;
		return "Wrong file format " + message;
		
	}
}
