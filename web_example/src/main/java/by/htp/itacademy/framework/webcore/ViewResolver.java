package by.htp.itacademy.framework.webcore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewResolver {
	
	void resolveView(HttpServletRequest request, HttpServletResponse response, Object invorkResult);
	
}
