package framework.webcore;

import framework.webcore.bean.Handler;

public interface HandlerMapping {
	
	Handler getHandler(String requestPath, String requestMethod);
	
}
