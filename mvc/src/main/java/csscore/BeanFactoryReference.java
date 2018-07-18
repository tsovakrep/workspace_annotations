package framework.csscore;

public interface BeanFactoryReference {

	/**
	 * Return the {@link BeanFactory} instance held by this reference.
	 * @throws IllegalStateException if invoked after {@code release()} has been called
	 */
	BeanFactory getFactory();

	/**
	 * Indicate that the {@link BeanFactory} instance referred to by this object is not
	 * needed any longer by the client code which obtained the {@link BeanFactoryReference}.
	 * <p>Depending on the actual implementation of {@link BeanFactoryLocator}, and
	 * the actual type of {@code BeanFactory}, this may possibly not actually
	 * do anything; alternately in the case of a 'closeable' {@code BeanFactory}
	 * or derived class (such as {@link org.springframework.context.ApplicationContext})
	 * may 'close' it, or may 'close' it once no more references remain.
	 * <p>In an EJB usage scenario this would normally be called from
	 * {@code ejbRemove()} and {@code ejbPassivate()}.
	 * <p>This is safe to call multiple times.
	 * @see BeanFactoryLocator
	 * @see org.springframework.context.access.ContextBeanFactoryReference
	 * @see org.springframework.context.ConfigurableApplicationContext#close()
	 */
	void release();

}

