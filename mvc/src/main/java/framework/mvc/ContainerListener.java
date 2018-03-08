package framework.mvc;

import org.apache.commons.lang.StringUtils;
import framework.FrameworkConstant;
import framework.HelperLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContainerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();

		// ���������������Helper
		HelperLoader.init();

		// ������servlet������
		addServletMapping(servletContext);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

	private void addServletMapping(ServletContext servletContext) {
		// ���DefaultServlet������������������������
		registerDefaultServlet(servletContext);
		// ���JspServlet������������jsp������
		registerJspServlet(servletContext);
	}

	private void registerJspServlet(ServletContext servletContext) {
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping("/index.jsp");
		String jspPath = FrameworkConstant.JSP_PATH;
		if (StringUtils.isNotEmpty(jspPath)) {
			jspServlet.addMapping(jspPath);
		}
	}

	private void registerDefaultServlet(ServletContext servletContext) {
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping("/index.html");
		defaultServlet.addMapping("/favicon.ico");
		String resourcePath = FrameworkConstant.RESOURCE_PATH;
		if (StringUtils.isNotEmpty(resourcePath)) {
			defaultServlet.addMapping(resourcePath);
		}
	}

}
