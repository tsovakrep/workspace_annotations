package by.htp.itacademy.listener;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import annotationapi.annotation.Controller;
import annotationapi.util.AnnotationFinder;
import annotationapi.util.FileFinder;


public class AppServletContextListener implements ServletContextListener {

	private static final String RESOURRCE_PATH_CLASSES = "/WEB-INF/classes/";
	private static final String RESOURRCE_PATH_PAGES = "/WEB-INF/pages/";
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		context.setAttribute("annotationfinder", getAnnotationFinder(context));
		context.setAttribute("pages", getPages(context));
		context.setAttribute("httpclient", getHttpClient());
		
		System.out.println("context initilized");
	}
	
	private Set<String> getPages(ServletContext context) {
		Set<String> pages = new HashSet<>();
		pages = new FileFinder().searchResourceFiles(context, RESOURRCE_PATH_PAGES, pages);
		return pages;
	}
	
	private AnnotationFinder getAnnotationFinder(ServletContext context) {
		Set<String> fileClass = new HashSet<>();
		fileClass = new FileFinder().searchResourceFiles(context, RESOURRCE_PATH_CLASSES, fileClass);

		AnnotationFinder af = new AnnotationFinder(Controller.class, context);
		
		for (String file : fileClass) {
			try {
				Class<?> clazz = Class.forName(file.replaceAll(RESOURRCE_PATH_CLASSES, "").replaceAll(".class", "").replaceAll("/", "."));
				af.searchAnnotation(clazz);			
				af.fillingMethodContainer(clazz);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return af;
	}
	
	private HttpClient getHttpClient() {
		return HttpClientBuilder.create().build();
	}
}
