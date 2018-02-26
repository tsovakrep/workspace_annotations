package by.htp.itacademy.listener;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


public class AppServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		ServletContext context = sce.getServletContext();
//		
//		Set<String> pages = new HashSet<>();
//		pages = findFilesInPath(context, "/WEB-INF/pages/", pages);
//		context.setAttribute("pages", pages);
//		
//		HttpClient httpClient = HttpClientBuilder.create().build();
//		context.setAttribute("httpClient", httpClient);
		
		
		System.out.println("context initilized");
	}
	
	private Set<String> findFilesInPath(ServletContext context, String path, Set<String> temp) {
		Set<String> list = context.getResourcePaths(path);
		Set<String> linkedSet = new HashSet<>();
		linkedSet.addAll(list);
		
		for (String file : linkedSet) {
			if(file.endsWith("/")) {
				findFilesInPath(context, file, temp);
			} else {
				temp.add(file);
			}
		}
		return temp;
	}
}
