package by.htp.itacademy.framework.webcore;

import by.htp.itacademy.framework.webcore.bean.Handler;

public interface HandlerMapping {
	
	Handler getHandler(String requestPath, String requestMethod);
	
}
