package framework.webcore.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import framework.classcore.ClassScanner;
import framework.classcore.PageScanner;
import framework.classcore.impl.ClassScannerImpl;
import framework.classcore.impl.PageScannerImpl;
import framework.util.ObjectUtils;
import framework.webcore.HandlerInvoker;
import framework.webcore.HandlerMapping;
import framework.webcore.ViewResolver;
import framework.webcore.impl.HandlerInvokerImpl;
import framework.webcore.impl.HandlerMappingImpl;
import framework.webcore.impl.ViewResolverImpl;

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
	private static final String PAGE_SCANNER = "page_scanner";

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
    
    public static PageScanner getPageScanner() {
    	return getInstance(PAGE_SCANNER, PageScannerImpl.class);
    }

	@SuppressWarnings("unchecked")
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