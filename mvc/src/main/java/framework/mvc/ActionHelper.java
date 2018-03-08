package framework.mvc;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import framework.core.ClassHelper;
import framework.mvc.exception.InitializationException;
import framework.mvc.annotation.Controller;
import framework.mvc.annotation.RequestMapping;
import framework.mvc.bean.Handler;
import framework.mvc.bean.Requester;
import framework.mvc.util.RequestMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionHelper {
	private static Logger logger = LoggerFactory.getLogger(ActionHelper.class);
	private static final Map<Requester, Handler> actionMap = new HashMap<Requester, Handler>();
	static {
		try {
			List<Class<?>> actionClassList = ClassHelper.getClassListByAnnotation(Controller.class);
			Map<Requester, Handler> commonActionMap = new HashMap<Requester, Handler>();
			if (CollectionUtils.isNotEmpty(actionClassList)) {
				for (int i = 0; i < actionClassList.size(); i++) {
					Class<?> actionClass = actionClassList.get(i);
					Method[] actionMethods = actionClass.getDeclaredMethods();
					if (!ArrayUtils.isEmpty(actionMethods)) {
						for (Method actionMethod : actionMethods) {
							HandleActionMethod(actionClass, actionMethod, commonActionMap);
						}
					}
				}

				actionMap.putAll(commonActionMap);
				printAllAction(actionClassList);
			}
		} catch (Exception e) {
			throw new InitializationException("Action���������������", e);
		}
	}

	private static void printAllAction(List<Class<?>> actionClassList) {
		StringBuilder builder = new StringBuilder();
		if (CollectionUtils.isNotEmpty(actionClassList)) {
			for (int i = 0; i < actionClassList.size(); i++) {
				Class<?> actionClass = actionClassList.get(i);
				builder.append(actionClass.getSimpleName());
				if (i < actionClassList.size() - 1) {
					builder.append("���");
				}
			}
		}
		logger.info("Action name: [{}]", builder.toString());
	}

	private static void HandleActionMethod(Class<?> actionClass, Method actionMethod,
			Map<Requester, Handler> actionMap) {
		if (actionMethod.isAnnotationPresent(RequestMapping.class)) {
			String requestUrl = actionMethod.getAnnotation(RequestMapping.class).url();
			RequestMethod[] requestMethods = actionMethod.getAnnotation(RequestMapping.class).method();
			putActionMap(requestMethods, requestUrl, actionClass, actionMethod, actionMap);
		}
	}

	private static void putActionMap(RequestMethod[] requestMethods, String requestUrl, Class<?> actionClass,
			Method actionMethod, Map<Requester, Handler> actionMap) {
		// ������requestUrl������������������������
		if (requestUrl.matches(".+\\{\\w+\\}.*")) {
			requestUrl.replaceAll("\\{\\w+\\}", "(\\\\\\w+)");
		}
		actionMap.put(new Requester(requestUrl, requestMethods), new Handler(actionClass, actionMethod));
	}

	public static Map<Requester, Handler> getActionMap() {
		return actionMap;
	}
}
