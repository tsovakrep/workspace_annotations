package by.htp.itacademy.listener;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class AppServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("context Destroyed!");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		
		
		Set<String> list = context.getResourcePaths("/WEB-INF/page");
		context.setAttribute("pages", list);
//		for (String string : list) {
//			System.out.println(string);
//		}
		
//		for (Map.Entry<String, ? extends ServletRegistration> servlet : context.getServletRegistrations().entrySet()) {
//			ServletRegistration servletReg = servlet.getValue();
//			if (servletReg.getClassName().startsWith("by.htp")) {
////				System.out.println(servlet.getKey() + " : " + servletReg.getClassName());
//				for (String url : servletReg.getMappings()) {
////					System.out.println("url mapping" + " : " + url);
//				}
//				if (AppServletDispatcher.class.getName().equals(servlet.getValue().getClassName())) {
//					servletReg.addMapping("/");
//					servletReg.addMapping("/go/ts");
//					servletReg.addMapping("/welcome");
//					servletReg.addMapping("/welcome/tsovak");
//					servletReg.addMapping("/welcome/palakian");
//				}
//			}
//		}

		System.out.println("context initilized");
		// ServletRegistration.Dynamic asr = context.
		// ServletRegistration.Dynamic asr =
		// context.addServlet(WelcomeServlet.class.getSimpleName(),
		// WelcomeServlet.class);
		// if (asr != null) {
		// asr.setLoadOnStartup(0);
		// asr.addMapping("/welcome");
		// asr.addMapping("/welcome/tsovak");
		// asr.addMapping("/root");
		//
		// } else {
		// System.out.println("servlet WelcomServlet allready exists");
		// }
	}
}
