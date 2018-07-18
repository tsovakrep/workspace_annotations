package framework.csscore;

public interface EnvironmentCapable {

	/**
	 * Return the {@link Environment} associated with this component
	 * (may be {@code null} or a default environment).
	 */
	Environment getEnvironment();

}

