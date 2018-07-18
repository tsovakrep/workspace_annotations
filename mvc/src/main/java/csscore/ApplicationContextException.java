package framework.csscore;

@SuppressWarnings("serial")
public class ApplicationContextException extends FatalBeanException {

	/**
	 * Create a new {@code ApplicationContextException}
	 * with the specified detail message and no root cause.
	 * @param msg the detail message
	 */
	public ApplicationContextException(String msg) {
		super(msg);
	}

	/**
	 * Create a new {@code ApplicationContextException}
	 * with the specified detail message and the given root cause.
	 * @param msg the detail message
	 * @param cause the root cause
	 */
	public ApplicationContextException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
