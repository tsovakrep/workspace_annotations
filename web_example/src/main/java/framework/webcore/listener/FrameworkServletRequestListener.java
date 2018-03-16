package framework.webcore.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class FrameworkServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("Hi from FrameworkServletRequestListener!");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		
	}
}
