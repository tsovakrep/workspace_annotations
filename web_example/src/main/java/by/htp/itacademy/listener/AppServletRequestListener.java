package by.htp.itacademy.listener;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import by.htp.itacademy.controller.AppServletDispatcher;

@WebListener
public class AppServletRequestListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent sre) {

		
//		for (Map.Entry<String, ? extends ServletRegistration> servlet : sre.getServletContext().getServletRegistrations().entrySet()) {
//			ServletRegistration servletReg = servlet.getValue();
//			if (servletReg.getClassName().startsWith("by.htp")) {
//				System.out.println(servlet.getKey() + " : " + servletReg.getClassName());
//				for (String url : servletReg.getMappings()) {
//					System.out.println("url mapping" + " : " + url);
//				}
////				if (AppServletDispatcher.class.getName().equals(servlet.getValue().getClassName())) {
////					servletReg.addMapping("/");
////					servletReg.addMapping("/go/ts");
////					servletReg.addMapping("/welcome");
////					servletReg.addMapping("/welcome/tsovak");
////					servletReg.addMapping("/welcome/palakian");
////				}
//			}
//		}
		
		
//		ServletRequest sr = sre.getServletRequest();
//		HttpServletRequest httpServletRequest = (HttpServletRequest) sr;
//		HttpSession httpSession = httpServletRequest.getSession();
//		System.out.println("language: " + httpSession.getAttribute("language"));
//		
//		String uri = httpServletRequest.getRequestURI();
//		System.out.println(uri);
//		System.out.println("sr initialized: " + sr);
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
//		ServletRequest sr = sre.getServletRequest();
//		System.out.println("sr destroyed: " + sr);
	}
}
