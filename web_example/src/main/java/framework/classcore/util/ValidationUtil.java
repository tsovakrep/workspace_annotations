package framework.classcore.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import framework.classcore.annotation.validation.Validation;
import framework.classcore.exception.IllegalParameterException;

public class ValidationUtil {
	
	public static void validator(Object obj, Map<String, String> regexParameters) 
			throws IllegalArgumentException, IllegalAccessException, Exception {
		
		for (Field field : MethodUtil.getDeclaredFields(obj)) {
			if (field.isAnnotationPresent(Validation.class)) {
				field.setAccessible(true);
				checkObject(field.get(obj), regexParameters);
			}
		}
	}
	
	private static void checkObject(Object obj, Map<String, String> regexParameters)
			throws Exception {
		
		Map<String, Object> fieldNameAndValue = getValuesFromEntity(obj);
		
		for (Entry<String, String> regex : regexParameters.entrySet()) {
			for (Entry<String, Object> value : fieldNameAndValue.entrySet()) {
				
				if (regex.getKey().contains((value.getKey()))) {
					
					Pattern pattern = Pattern.compile(regex.getValue());
					Matcher matcher = pattern.matcher(value.getValue().toString());
					if (!matcher.matches()) {
						throw new IllegalParameterException();
					}
				}
			}
		}
	}
	
	private static Map<String, Object> getValuesFromEntity(Object obj) 
			throws Exception {
		
		if (obj == null) {
			throw new IllegalArgumentException(); 
		}
		
		Map<String, Object> fieldNameAndValue = new ConcurrentHashMap<String, Object>();
		
		Field[] fields = MethodUtil.getDeclaredFields(obj);
		
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.get(obj) != null) {
				fieldNameAndValue.put(field.getName(), field.get(obj)); 
			}
		}
		return fieldNameAndValue;
	}
}