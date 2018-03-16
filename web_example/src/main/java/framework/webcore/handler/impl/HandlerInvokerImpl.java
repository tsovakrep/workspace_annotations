package framework.webcore.handler.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import framework.webcore.bean.Handler;
import framework.webcore.handler.HandlerInvoker;
import framework.webcore.helper.BeanHelper;
import framework.webcore.util.ParameterUtil;

public class HandlerInvokerImpl implements HandlerInvoker {

	@Override
	public Object invokeHandler(HttpServletRequest request, Handler handler)
			throws InvocationTargetException, IllegalAccessException {

		Class<?> actionClass = handler.getActionClass();
		Method actionMethod = handler.getActionMethod();
		Object actionInstance = BeanHelper.getBean(actionClass);
		List<Object> paramList = createActionMethodParamsList(request, actionMethod, handler);
		Object invokeResult = invokeActionMethod(actionMethod, actionInstance, paramList);
		return invokeResult;
	}

	private List<Object> createActionMethodParamsList(HttpServletRequest request, Method actionMethod,
			Handler handler) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(ParameterUtil.createPathParamList(request, actionMethod, handler));
		return paramList;
	}

	private Object invokeActionMethod(Method actionMethod, Object actionInstance, List<Object> paramList)
			throws InvocationTargetException, IllegalAccessException {
		actionMethod.setAccessible(true);
		Object actionInvorkResult = actionMethod.invoke(actionInstance, paramList.toArray());
		return actionInvorkResult;
	}
}
