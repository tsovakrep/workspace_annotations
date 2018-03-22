package framework.util;

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
        return !isEmptyString(str);
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
	
	public static boolean isNullClass(Class<?> clazz) {
		return clazz == null;
	}
	
	public static boolean isNotNullClass(Class<?> clazz) {
		return !isNullClass(clazz);
	}
	
	public static boolean isEqueals(Object obj, Class<?> clazz) {
		return obj.getClass().getTypeName().equals(clazz.getName());
	}
	
	public static boolean isNotEqueals(Object obj, Class<?> clazz) {
		return !obj.getClass().getTypeName().equals(clazz.getName());
	}
	
	public static boolean isEquals(String str1, String str2) {
		return str1 != null && str1.equals(str2) ? true : str1 == str2; 
	}
	
	public static boolean isNotEquals(String str1, String str2) {
		return !isEquals(str1, str2);
	}
	
	public static boolean isEquals(Integer int1, Integer int2) {
		return int1 != null && int1.equals(int2) ? true : int1 == int2; 
	}
	
	public static boolean isNotEquals(Integer int1, Integer int2) {
		return !isEquals(int1, int2);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T cast(Object obj) {
        return (T) obj;
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
