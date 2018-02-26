package annotationapi.util.exception;

@SuppressWarnings("serial")
public class ClassFindException extends Exception {
	public ClassFindException() {
		super();
	}

	public ClassFindException(String message) {
		super(message);
	}

	public ClassFindException(Throwable cause) {
		super(cause);
	}

	public ClassFindException(String message, Throwable cause) {
		super(message, cause);
	}
}
