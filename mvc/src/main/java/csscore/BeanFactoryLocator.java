package framework.csscore;

public interface BeanFactoryLocator {

	/**
	 * Use the {@link org.springframework.beans.factory.BeanFactory} (or derived
	 * interface such as {@link org.springframework.context.ApplicationContext})
	 * specified by the {@code factoryKey} parameter.
	 * <p>The definition is possibly loaded/created as needed.
	 * @param factoryKey a resource name specifying which {@code BeanFactory} the
	 * {@code BeanFactoryLocator} must return for usage. The actual meaning of the
	 * resource name is specific to the implementation of {@code BeanFactoryLocator}.
	 * @return the {@code BeanFactory} instance, wrapped as a {@link BeanFactoryReference} object
	 * @throws BeansException if there is an error loading or accessing the {@code BeanFactory}
	 */
	BeanFactoryReference useBeanFactory(String factoryKey) throws BeansException;

}