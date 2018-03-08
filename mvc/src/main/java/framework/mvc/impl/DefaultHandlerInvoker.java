package framework.mvc.impl;

import org.apache.commons.collections.MapUtils;
import framework.ioc.BeanHelper;
import framework.mvc.HandlerInvoker;
import framework.mvc.bean.Handler;
import framework.mvc.bean.Params;
import framework.mvc.util.CastUtil;
import framework.mvc.util.WebUtil;
import framework.util.ClassUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class DefaultHandlerInvoker implements HandlerInvoker {

	@Override
	public Object invokeHandler(HttpServletRequest request, Handler handler)
			throws InvocationTargetException, IllegalAccessException {
		
		Class<?> actionClass = handler.getActionClass();
		Method actionMethod = handler.getActionMethod();
		Object actionInstance = BeanHelper.getBean(actionClass);
		List<Object> paramList = createActionMethodParamsList(request, handler);
		Object invorkResult = invorkActionMethod(actionMethod, actionInstance, paramList);
		return invorkResult;
	}

	private Object invorkActionMethod(Method actionMethod, Object actionInstance, List<Object> paramList)
			throws InvocationTargetException, IllegalAccessException {
		actionMethod.setAccessible(true);
		Object actionInvorkResult = actionMethod.invoke(actionInstance, paramList.toArray());
		return actionInvorkResult;
	}

	private List<Object> createActionMethodParamsList(HttpServletRequest request, Handler handler) {
		List<Object> paramList = new ArrayList<Object>();
		Class<?>[] parameterTypes = handler.getActionMethod().getParameterTypes();
		paramList.addAll(createPathParamList(parameterTypes, handler));
		Map<String, Object> requestParamMap = WebUtil.getRequestParamMap(request);
		if (MapUtils.isNotEmpty(requestParamMap)) {
			paramList.add(new Params(requestParamMap));
		}
		return paramList;
	}

	private List<Object> createPathParamList(Class<?>[] parameterTypes, Handler handler) {
		List<Object> paramList = new ArrayList<Object>();
		Matcher requestMatcher = handler.getRequestMatcher();
		for (int i = 1; i <= requestMatcher.groupCount(); i++) {
			String paramValue = requestMatcher.group(i);
			Class<?> parameterType = parameterTypes[i - 1];
			if (ClassUtil.isInt(parameterType)) {
				paramList.add(CastUtil.castInteger(paramValue));
			} else if (ClassUtil.isDouble(parameterType)) {
				paramList.add(CastUtil.castDouble(paramValue));
			} else if (ClassUtil.isLong(parameterType)) {
				paramList.add(CastUtil.castLong(paramValue));
			} else if (ClassUtil.isString(parameterType)) {
				paramList.add((paramValue));
			}
		}
		return paramList;
	}
}
