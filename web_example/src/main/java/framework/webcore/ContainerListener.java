package framework.webcore;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import framework.classcore.PageHelper;
import framework.util.FrameworkConstant;
import framework.util.HelperLoader;
import framework.util.ObjectUtils;

@WebListener
public class ContainerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		DataContext.getInstance(servletContext);

		HelperLoader.init();

		addServletMapping(servletContext);
		
		Map<String, ? extends ServletRegistration> map = servletContext.getServletRegistrations();
		for (Entry<String, ? extends ServletRegistration> sr : map.entrySet()) {
			for (String c : sr.getValue().getMappings()) {
				System.out.println(c);
			}
		}

		System.out.println("context initilized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("context destroyed");
	}

	private void addServletMapping(ServletContext servletContext) {
		List<String> pages = PageHelper.getBasePackagePageList();
		registerJspServlet(servletContext, pages);
		registerDefaultServlet(servletContext);
		
	}

	private void registerJspServlet(ServletContext servletContext, List<String> pages) {
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		for (String page : pages) {
			if (ObjectUtils.isNotEmptyString(page)) {
				jspServlet.addMapping(page);
			}
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
