package by.htp.itacademy.framework.webcore.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.htp.itacademy.framework.classcore.ClassScanner;
import by.htp.itacademy.framework.classcore.impl.ClassScannerImpl;
import by.htp.itacademy.framework.util.ObjectUtils;
import by.htp.itacademy.framework.webcore.HandlerInvoker;
import by.htp.itacademy.framework.webcore.HandlerMapping;
import by.htp.itacademy.framework.webcore.ViewResolver;
import by.htp.itacademy.framework.webcore.impl.HandlerInvokerImpl;
import by.htp.itacademy.framework.webcore.impl.HandlerMappingImpl;
import by.htp.itacademy.framework.webcore.impl.ViewResolverImpl;

/**
 * @author Tsovak Palakian
 *
 */
public class InstanceFactory {
	private static final Map<String, Object> CACHE_MAP = new ConcurrentHashMap<String, Object>();

	private static final String CLASS_SCANNER = "class_scanner";
	private static final String HANDLER_INVOKER = "handler_invoker";
	private static final String HANDLER_MAPPING = "handler_mapping";
	private static final String VIEW_RESOLVER = "view_resolver";

	public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, ClassScannerImpl.class);
    }

    public static HandlerMapping getHandlerMapping() {
        return getInstance(HANDLER_MAPPING, HandlerMappingImpl.class);
    }

    public static HandlerInvoker getHandlerInvoker() {
        return getInstance(HANDLER_INVOKER, HandlerInvokerImpl.class);
    }

    public static ViewResolver getViewResolver() {
        return getInstance(VIEW_RESOLVER, ViewResolverImpl.class);
    }

	private static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
		if (CACHE_MAP.containsKey(cacheKey)) {
			return (T) CACHE_MAP.get(cacheKey);
		}

		String implementClassName = defaultImplClass.getName();

		T instance = ObjectUtils.newInstance(implementClassName);
		if (instance != null) {
			CACHE_MAP.put(cacheKey, instance);
		}
		return instance;
	}
	
}
