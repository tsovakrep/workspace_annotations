package by.htp.itacademy.listener;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

@WebListener
public class AppServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		//ServletRequest sr = sre.getServletRequest();
		//System.out.println("sr destroyed: " + sr);
		//ServletRegistration serReg = (ServletRegistration) sr.getAttribute("welcomeserv");
		//System.out.println("attribute: " + sr.getAttribute("welcomeserv"));
		//serReg.addMapping("welcome/tsovakpalakian");
//		String tsovak = sr.getAttribute("tsovak").toString();
//		System.out.println(tsovak);
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
//		ServletContext servletContext = sre.getServletContext();
//		HttpClient httpClient = (HttpClient) servletContext.getAttribute("httpClient");
//		
//		HttpGet request = new HttpGet("");
//
//		try {
//			HttpResponse response = httpClient.execute(request);
//			HttpEntity entity = response.getEntity();
//
//			// Read the contents of an entity and return it as a String.
//			String content = EntityUtils.toString(entity);
//			System.out.println(content);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		ServletRequest sr = sre.getServletRequest();
//		System.out.println("sr initialized: " + sr);
	}

}