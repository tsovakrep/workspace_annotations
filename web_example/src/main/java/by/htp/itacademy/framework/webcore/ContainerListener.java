package by.htp.itacademy.framework.webcore;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import by.htp.itacademy.framework.util.FrameworkConstant;
import by.htp.itacademy.framework.util.HelperLoader;
import by.htp.itacademy.framework.util.ObjectUtils;

@WebListener
public class ContainerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();

		HelperLoader.init();

		addServletMapping(servletContext);
		
		System.out.println("context initilized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("context destroyed");
	}

	private void addServletMapping(ServletContext servletContext) {
		registerDefaultServlet(servletContext);
		registerJspServlet(servletContext);
	}

	private void registerJspServlet(ServletContext servletContext) {
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		String jspPath = FrameworkConstant.PATH_PAGES;
		if (ObjectUtils.isNotEmptyString(jspPath)) {
			jspServlet.addMapping(jspPath);
		}
	}

	private void registerDefaultServlet(ServletContext servletContext) {
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		String resourcePath = FrameworkConstant.PATH_PAGES;
		if (ObjectUtils.isNotEmptyString(resourcePath)) {
			defaultServlet.addMapping(resourcePath);
		}
	}

}
