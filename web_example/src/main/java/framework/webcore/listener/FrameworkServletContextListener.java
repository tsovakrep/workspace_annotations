package framework.webcore.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import framework.classcore.helper.PageHelper;
import framework.util.FrameworkConstant;
import framework.util.ObjectUtils;
import framework.webcore.DataContext;
import framework.webcore.helper.HelperLoader;

@WebListener
public class FrameworkServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		DataContext.getInstance(servletContext);

		HelperLoader.init();
		
		List<String> pages = PageHelper.getBasePackagePageList();
		servletContext.setAttribute("listOfPages", pages);
		addServletMapping(servletContext, pages);

		System.out.println("context initilized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("context destroyed");
	}

	private void addServletMapping(ServletContext servletContext, List<String> pages) {
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
		defaultServlet.addMapping(resourcePath);
	}

}
