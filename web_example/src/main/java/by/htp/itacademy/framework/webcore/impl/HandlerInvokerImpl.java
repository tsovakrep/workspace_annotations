package by.htp.itacademy.framework.webcore.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.itacademy.framework.webcore.BeanHelper;
import by.htp.itacademy.framework.webcore.HandlerInvoker;
import by.htp.itacademy.framework.webcore.bean.Handler;
import by.htp.itacademy.framework.webcore.util.WebUtil;

public class HandlerInvokerImpl implements HandlerInvoker {

	@Override
	public Object invokeHandler(HttpServletRequest request, Handler handler)
			throws InvocationTargetException, IllegalAccessException {

		Class<?> actionClass = handler.getActionClass();
		Method actionMethod = handler.getActionMethod();
		Annotation[] annotations = actionMethod.getAnnotations();
		Object actionInstance = BeanHelper.getBean(actionClass);
		List<Object> paramList = createActionMethodParamsList(request, annotations, handler);
		Object invokeResult = invokeActionMethod(actionMethod, actionInstance, paramList);
		return invokeResult;
	}

	private List<Object> createActionMethodParamsList(HttpServletRequest request, Annotation[] annotations,
			Handler handler) {
		List<Object> paramList = new ArrayList<Object>();
		Class<?>[] parameterTypes = handler.getActionMethod().getParameterTypes();
		paramList.addAll(WebUtil.createPathParamList(request, parameterTypes, annotations, handler));
		// Map<String, Object> requestParamMap = WebUtil.getRequestParamMap(request);
		// if (MapUtils.isNotEmpty(requestParamMap)) {
		// paramList.add(new Params(requestParamMap));
		// }
		return paramList;
	}

	private Object invokeActionMethod(Method actionMethod, Object actionInstance, List<Object> paramList)
			throws InvocationTargetException, IllegalAccessException {
		actionMethod.setAccessible(true);
		Object actionInvorkResult = actionMethod.invoke(actionInstance, paramList.toArray());
		return actionInvorkResult;
	}
}
