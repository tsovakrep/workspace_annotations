package framework.webcore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.classcore.ClassHelper;
import framework.util.ObjectUtils;
import framework.webcore.annotation.Controller;
import framework.webcore.exception.InitializationException;

public class BeanHelper {

	private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

	static {
		try {
			List<Class<?>> basePackageClassList = ClassHelper.getBasePackageClassList();
			List<Class<?>> beanClassList = new ArrayList<Class<?>>();

			if (ObjectUtils.isNotEmptyCollection(basePackageClassList)) {
				for (int i = 0; i < basePackageClassList.size(); i++) {
					Class<?> cls = basePackageClassList.get(i);
					if (cls.isAnnotationPresent(Controller.class)) {
						beanClassList.add(cls);
						Object instance = cls.newInstance();
						beanMap.put(cls, instance);
					}
				}
			}
		} catch (Exception e) {
			throw new InitializationException("Class not found!", e);
		}
	}
	
	public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    @SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) {
            throw new RuntimeException("The class not contains annotation Controller.");
        }
        return (T) beanMap.get(cls);
    }
}
