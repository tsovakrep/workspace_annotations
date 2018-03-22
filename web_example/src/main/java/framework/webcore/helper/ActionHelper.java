package framework.webcore.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.classcore.helper.ClassHelper;
import framework.util.ObjectUtils;
import framework.webcore.annotation.controller.Controller;
import framework.webcore.annotation.controller.Mapping;
import framework.webcore.annotation.controller.method.DeleteMapping;
import framework.webcore.annotation.controller.method.GetMapping;
import framework.webcore.annotation.controller.method.PostMapping;
import framework.webcore.annotation.controller.method.PutMapping;
import framework.webcore.bean.Handler;
import framework.webcore.bean.Requester;
import framework.webcore.exception.InitializationException;
import framework.webcore.http.HttpMethod;

import static framework.util.FrameworkConstant.*;

public class ActionHelper {
	
	private static final Map<Requester, Handler> actionMap = new HashMap<Requester, Handler>();
	
	static {
		try {
			List<Class<?>> actionClassList = ClassHelper.getClassListByAnnotation(Controller.class);
			Map<Requester, Handler> commonActionMap = new HashMap<Requester, Handler>();
			
			if (ObjectUtils.isNotEmptyCollection(actionClassList)) {
				for (int i = 0; i < actionClassList.size(); i++) {
					Class<?> actionClass = actionClassList.get(i);
					handlerActionClassAndMethod(actionClass, commonActionMap);
				}
			}
			
			actionMap.putAll(commonActionMap);
		} catch (Exception e) {
			throw new InitializationException(e);
		}
	}
	
	public static Map<Requester, Handler> getActionMap() {
		return actionMap;
	}
	
	private static void handlerActionClassAndMethod(Class<?> actionClass, Map<Requester, Handler> commonActionMap) {
		if (actionClass.isAnnotationPresent(Mapping.class)) {
			String classMappingUrl = actionClass.getAnnotation(Mapping.class).value();
			if (!classMappingUrl.startsWith(SLASH)) {
				String slash = SLASH;
				classMappingUrl = slash.concat(classMappingUrl);
			}
			getClassMappingValue(classMappingUrl, actionClass, commonActionMap);
		}
	}
	
	private static void getClassMappingValue(String classMappingUrl, Class<?> actionClass, Map<Requester, Handler> commonActionMap) {
		Method[] actionMethods = actionClass.getMethods();
		if (ObjectUtils.isNotEmptyArray(actionMethods)) {
			getMethodMappingValue(classMappingUrl, actionClass, actionMethods, commonActionMap);
		}
	}
	
	private static void getMethodMappingValue(String classMappingUrl, Class<?> actionClass, Method[] classMethods, Map<Requester, Handler> commonActionMap) {
		for(int i = 0; i < classMethods.length; i++) {
			
			checkGETAnnot(classMappingUrl, actionClass, classMethods[i], commonActionMap);
			
			checkPOSTAnnot(classMappingUrl, actionClass, classMethods[i], commonActionMap);
			
			checkPUTAnnot(classMappingUrl, actionClass, classMethods[i], commonActionMap);
			
			checkDELETEAnnot(classMappingUrl, actionClass, classMethods[i], commonActionMap);
		}
	}
	
	private static void checkGETAnnot(String classMappingUrl, Class<?> actionClass, Method classMethod, Map<Requester, Handler> commonActionMap) {
		if (classMethod.isAnnotationPresent(GetMapping.class)) {
			String getMappingUrl = classMethod.getAnnotation(GetMapping.class).value();
			String requestUrl = classMappingUrl + getMappingUrl;
			putActionMap(HttpMethod.GET, requestUrl, actionClass, classMethod, commonActionMap);
		}
	}
	
	private static void checkPOSTAnnot(String classMappingUrl, Class<?> actionClass, Method classMethod, Map<Requester, Handler> commonActionMap) {
		if (classMethod.isAnnotationPresent(PostMapping.class)) {
			String getMappingUrl = classMethod.getAnnotation(PostMapping.class).value();
			String requestUrl = classMappingUrl + getMappingUrl;
			putActionMap(HttpMethod.POST, requestUrl, actionClass, classMethod, commonActionMap);
		}
	}
	
	private static void checkPUTAnnot(String classMappingUrl, Class<?> actionClass, Method classMethod, Map<Requester, Handler> commonActionMap) {
		if (classMethod.isAnnotationPresent(PutMapping.class)) {
			String getMappingUrl = classMethod.getAnnotation(PutMapping.class).value();
			String requestUrl = classMappingUrl + getMappingUrl;
			putActionMap(HttpMethod.PUT, requestUrl, actionClass, classMethod, commonActionMap);
		}
	}
	
	private static void checkDELETEAnnot(String classMappingUrl, Class<?> actionClass, Method classMethod, Map<Requester, Handler> commonActionMap) {
		if (classMethod.isAnnotationPresent(DeleteMapping.class)) {
			String getMappingUrl = classMethod.getAnnotation(DeleteMapping.class).value();
			String requestUrl = classMappingUrl + getMappingUrl;
			putActionMap(HttpMethod.DELETE, requestUrl, actionClass, classMethod, commonActionMap);
		}
	}
	
	private static void putActionMap(HttpMethod requestMethods, String requestUrl, Class<?> actionClass, Method actionMethod, Map<Requester, Handler> commonActionMap) {
		commonActionMap.put(new Requester(requestUrl, requestMethods), new Handler(actionClass, actionMethod));
	}
}
