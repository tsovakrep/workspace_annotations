package framework.mvc.util;

import org.apache.commons.lang.StringUtils;
import framework.core.ClassScanner;
import framework.core.ConfigHelper;
import framework.core.impl.DefaultClassScanner;
import framework.mvc.HandlerInvoker;
import framework.mvc.HandlerMapping;
import framework.mvc.ViewResolver;
import framework.mvc.bean.Handler;
import framework.mvc.impl.DefaultHandlerInvoker;
import framework.mvc.impl.DefaultHandlerMapping;
import framework.mvc.impl.DefaultViewResolver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InstanceFactory {

    private static final Map<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();

    private static final String CLASS_SCANNER = "smart.framework.custom.class_scanner";

    private static final String HANDLER_MAPPING = "smart.framework.custom.handler_mapping";

    private static final String HANDLER_iNVOKER = "smart.framework.custom.handler_invoker";

    private static final String VIEW_RESOLVER = "smart.framework.custom.view_resolver";

    public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }

    public static HandlerMapping getHandlerMapping() {
        return getInstance(HANDLER_MAPPING, DefaultHandlerMapping.class);
    }

    public static HandlerInvoker getHandlerInvoker() {
        return getInstance(HANDLER_iNVOKER, DefaultHandlerInvoker.class);
    }

    public static ViewResolver getViewResolver() {
        return getInstance(VIEW_RESOLVER, DefaultViewResolver.class);
    }

    private static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        if (cacheMap.containsKey(cacheKey)) {
            return (T) cacheMap.get(cacheKey);
        }

        String implementClassName = ConfigHelper.getString(cacheKey);
        if (StringUtils.isEmpty(implementClassName)) {
            implementClassName = defaultImplClass.getName();
        }
        T instance = ObjectUtils.newInstance(implementClassName);
        if (instance != null) {
            cacheMap.put(cacheKey, instance);
        }
        return instance;
    }
}
