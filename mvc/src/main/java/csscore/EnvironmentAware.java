package framework.csscore;

public interface EnvironmentAware extends Aware {

	/**
	 * Set the {@code Environment} that this component runs in.
	 */
	void setEnvironment(Environment environment);

}

