package framework.webcore.responseentity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ResponseEntityResolver {
	
	void resolveResponseEntity(HttpServletRequest request, HttpServletResponse response, Object invokResult);
	
}
