package framework.webcore;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.classcore.ClassHelper;
import framework.util.ObjectUtils;
import framework.webcore.annotation.Controller;
import framework.webcore.annotation.DeleteMapping;
import framework.webcore.annotation.GetMapping;
import framework.webcore.annotation.Mapping;
import framework.webcore.annotation.PostMapping;
import framework.webcore.annotation.PutMapping;
import framework.webcore.bean.Handler;
import framework.webcore.bean.Requester;
import framework.webcore.exception.InitializationException;
import framework.webcore.util.HttpMethod;


public class ActionHelper {
	private static final Map<Requester, Handler> actionMap = new HashMap<Requester, Handler>();
	
	static {
		try {
			List<Class<?>> actionClassList = ClassHelper.getClassListByAnnotation(Controller.class);
			System.out.println(actionClassList);
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
	
	private static void handlerActionClassAndMethod(Class<?> actionClass, Map<Requester, Handler> commonActionMap) {
		if (actionClass.isAnnotationPresent(Mapping.class)) {
			String classMappingUrl = actionClass.getAnnotation(Mapping.class).value();
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
			if (classMethods[i].isAnnotationPresent(GetMapping.class)) {
				String getMappingUrl = classMethods[i].getAnnotation(GetMapping.class).value();
				String requestUrl = classMappingUrl + getMappingUrl;
				putActionMap(HttpMethod.GET, requestUrl, actionClass, classMethods[i], commonActionMap);
			}
			
			if (classMethods[i].isAnnotationPresent(PostMapping.class)) {
				String getMappingUrl = classMethods[i].getAnnotation(PostMapping.class).value();
				String requestUrl = classMappingUrl + getMappingUrl;
				putActionMap(HttpMethod.POST, requestUrl, actionClass, classMethods[i], commonActionMap);
			}
			
			if (classMethods[i].isAnnotationPresent(PutMapping.class)) {
				String getMappingUrl = classMethods[i].getAnnotation(PutMapping.class).value();
				String requestUrl = classMappingUrl + getMappingUrl;
				putActionMap(HttpMethod.PUT, requestUrl, actionClass, classMethods[i], commonActionMap);
			}
			
			if (classMethods[i].isAnnotationPresent(DeleteMapping.class)) {
				String getMappingUrl = classMethods[i].getAnnotation(DeleteMapping.class).value();
				String requestUrl = classMappingUrl + getMappingUrl;
				putActionMap(HttpMethod.DELETE, requestUrl, actionClass, classMethods[i], commonActionMap);
			}
		}
	}
	
	private static void putActionMap(HttpMethod requestMethods, String requestUrl, 
			Class<?> actionClass, Method actionMethod, Map<Requester, Handler> commonActionMap) {
		if (requestUrl.matches(".+\\{\\w+\\}.*")) {
			requestUrl.replaceAll("\\{\\w+\\}", "(\\\\\\w+)");
		}
		commonActionMap.put(new Requester(requestUrl, requestMethods), new Handler(actionClass, actionMethod));
	}
	
	public static Map<Requester, Handler> getActionMap() {
		return actionMap;
	}
}
