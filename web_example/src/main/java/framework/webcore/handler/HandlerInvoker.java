package framework.webcore.handler;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import framework.webcore.bean.Handler;

public interface HandlerInvoker {

	Object invokeHandler(HttpServletRequest request, Handler handler)
			throws InvocationTargetException, IllegalAccessException;

}
