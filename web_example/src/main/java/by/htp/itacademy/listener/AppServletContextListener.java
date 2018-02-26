package by.htp.itacademy.listener;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class AppServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		ServletContext context = sce.getServletContext();
//		Set<String> pages = new HashSet<>();
//		pages = findFileInPath(context, "/WEB-INF/pages/", pages);
//		
//		context.setAttribute("pages", pages);

		System.out.println("context initilized");
	}
	
	private Set<String> findFileInPath(ServletContext context, String path, Set<String> temp) {
		Set<String> list = context.getResourcePaths(path);
		Set<String> linkedSet = new HashSet<>();
		linkedSet.addAll(list);
		
		for (String file : linkedSet) {
			if(file.endsWith("/")) {
				findFileInPath(context, file, temp);
			} else {
				temp.add(file);
			}
		}
		return temp;
	}
}
