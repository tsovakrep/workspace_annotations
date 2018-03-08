package framework.core.impl.support;

public abstract class SuperClassTemplate extends ClassTemplate {
	protected final Class<?> superClass;

	public SuperClassTemplate(String packageName, Class<?> cls) {
		super(packageName);
		superClass = cls;
	}
}
