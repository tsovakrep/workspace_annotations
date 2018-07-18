package framework.csscore;

public interface BeanClassLoaderAware extends Aware {

	/**
	 * Callback that supplies the bean {@link ClassLoader class loader} to
	 * a bean instance.
	 * <p>Invoked <i>after</i> the population of normal bean properties but
	 * <i>before</i> an initialization callback such as
	 * {@link InitializingBean InitializingBean's}
	 * {@link InitializingBean#afterPropertiesSet()}
	 * method or a custom init-method.
	 * @param classLoader the owning class loader; may be {@code null} in
	 * which case a default {@code ClassLoader} must be used, for example
	 * the {@code ClassLoader} obtained via
	 * {@link org.springframework.util.ClassUtils#getDefaultClassLoader()}
	 */
	void setBeanClassLoader(ClassLoader classLoader);

}

