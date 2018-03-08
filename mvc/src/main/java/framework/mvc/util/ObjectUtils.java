package framework.mvc.util;

import framework.util.ClassUtil;

public class ObjectUtils {

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className) {
		T t;
		try {
			Class<?> loadClass = ClassUtil.loadClass(className);
			t = (T) loadClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("?????????" + className + "??????", e);
		}
		return t;
	}

}
