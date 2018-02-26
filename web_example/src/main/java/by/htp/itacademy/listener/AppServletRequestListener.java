package by.htp.itacademy.listener;

import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

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
		//ServletRequest sr = sre.getServletRequest();
		//System.out.println("sr initialized: " + sr);
	}

}
