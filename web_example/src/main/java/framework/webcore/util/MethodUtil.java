package framework.webcore.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import framework.webcore.exception.IllegalParameterException;

public final class MethodUtil {

	private MethodUtil() {
	}

	protected static Class<?>[] getParameterTypes(Object obj, byte count) 
			throws IllegalParameterException {
		
		if (obj == null) {
			throw new IllegalParameterException("An object can not to be null!");
		}

		Constructor<?>[] constructors = obj.getClass().getConstructors();

		Class<?>[] parameterTypes = null;
		
		for (int i = 0; i < constructors.length; ++i) {
			if (constructors[i].getParameterCount() == count) {
				parameterTypes = constructors[i].getParameterTypes();
			}
		}
		
		if (parameterTypes == null) {
			throw new IllegalParameterException("A constructor with such a number of parameters does not exist!");
		}
		
		return null;
	}
	
	public static Field[] getDeclaredFields(Object obj) 
			throws IllegalParameterException {
		
		if (obj == null) {
			throw new IllegalParameterException("An object can not to be null!");
		}
		
		return obj.getClass().getDeclaredFields();
	}
	
	protected static Method[] getDeclaredMethods(Object obj) 
			throws IllegalParameterException {
		
		if (obj == null) {
			throw new IllegalParameterException("An object can not to be null!");
		}
		
		return obj.getClass().getDeclaredMethods();
	}
	
	protected static Class<?> getFieldClass(Field field) 
			throws ClassNotFoundException, IllegalParameterException {
		
		if (field == null) {
			throw new IllegalParameterException("A field can not to be null!");
		}
		
		return Class.forName(field.getType().getName());
	}

	protected static Constructor<?>[] getConstructors(Object obj) 
			throws IllegalParameterException, SecurityException, ClassNotFoundException {
		
		if (obj == null) {
			throw new IllegalParameterException("An object can not to be null!");
		}
		
		return Class.forName(obj.getClass().getName()).getConstructors();
	}
}
