package framework.webcore.handler;

import framework.webcore.bean.Handler;

public interface HandlerMapping {
	
	Handler getHandler(String requestPath, String requestMethod);
	
}
