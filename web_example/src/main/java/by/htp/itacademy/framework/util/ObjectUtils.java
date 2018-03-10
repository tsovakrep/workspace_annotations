package by.htp.itacademy.framework.util;

import java.util.Collection;
import java.util.Map;

public class ObjectUtils {
	
	public static boolean isNullObject(Object object) {
		return object == null;
	}
	
	public static boolean isNotNullObject(Object object) {
		return !isNullObject(object);
	}
	
	public static boolean isEmptyString(String str) {
        return str == null || str.length() == 0;
    }
	
	public static boolean isNotEmptyString(String str) {
        return isEmptyString(str);
    }
	
	public static boolean isEmptyArray(Object[] array) {
		return array == null || array.length == 0;
	}

	public static boolean isNotEmptyArray(Object[] array) {
		return !isEmptyArray(array);
	}
	
	public static boolean isEmptyCollection(Collection<?> coll) {
		return coll == null || coll.size() == 0;
	}

	public static boolean isNotEmptyCollection(Collection<?> coll) {
		return !isEmptyCollection(coll);
	}
	
	public static boolean isEmptyMap(Map<?, ?> map) {
		return map == null || map.size() == 0;
	}

	public static boolean isNotEmptyMap(Map<?, ?> map) {
		return !isEmptyMap(map);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className) {
		T t;
		try {
			Class<?> loadClass = ClassUtil.loadClass(className);
			t = (T) loadClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("The " + className + " could not be loaded.", e);
		}
		return t;
	}
}
