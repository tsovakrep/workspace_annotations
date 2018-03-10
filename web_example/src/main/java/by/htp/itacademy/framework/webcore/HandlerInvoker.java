package by.htp.itacademy.framework.webcore;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.framework.webcore.bean.Handler;

public interface HandlerInvoker {

	Object invokeHandler(HttpServletRequest request, Handler handler)
			throws InvocationTargetException, IllegalAccessException;

}
