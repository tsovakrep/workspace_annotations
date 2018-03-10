package by.htp.itacademy.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppServletRequestListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent sre) {

		
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
