package framework.mvc;

import framework.mvc.bean.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

public interface HandlerInvoker {
    Object invokeHandler(HttpServletRequest request, Handler handler) throws InvocationTargetException, IllegalAccessException;
}

