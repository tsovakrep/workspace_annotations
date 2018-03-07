package by.htp.itacademy.listener;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import annotationapi.annotation.Controller;
import annotationapi.util.AnnotationFinder;
import annotationapi.util.FileFinder;
import by.htp.itacademy.controller.AppServletDispatcher;


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
		
		for (Map.Entry<String, ? extends ServletRegistration> servlet : context.getServletRegistrations().entrySet()) {
			ServletRegistration servletReg = servlet.getValue();
			if (servletReg.getClassName().startsWith("by.htp")) {
				System.out.println(servlet.getKey() + " : " + servletReg.getClassName());
				for (String url : servletReg.getMappings()) {
					System.out.println("url mapping" + " : " + url);
				}
				if (AppServletDispatcher.class.getName().equals(servlet.getValue().getClassName())) {
					servletReg.addMapping("/");
					servletReg.addMapping("/go/ts");
					servletReg.addMapping("/welcome");
					servletReg.addMapping("/welcome/tsovak");
					servletReg.addMapping("/welcome/palakian");
				}
			}
		}
		
		context.setAttribute("annotationfinder", getAnnotationFinder(context));
		context.setAttribute("pages", getPages(context));
		
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
				af.fillMethodContainer(clazz);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return af;
	}
}
