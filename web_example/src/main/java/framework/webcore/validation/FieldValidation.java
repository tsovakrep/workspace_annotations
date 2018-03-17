package framework.webcore.validation;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import framework.webcore.annotation.validation.Validate;
import framework.webcore.exception.IllegalParameterException;

public class FieldValidation extends Validation {
	
	public static void validator(Object obj, Map<String, String> regexParameters) throws Exception {
		
		for (Field field : MethodUtil.getDeclaredFields(obj)) {
			if (field.isAnnotationPresent(Validate.class)) {
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
	
	@Override
	public Map<String, String> mapRegex(Object object) {
		return super.mapRegex(object);
	}

	@Override
	protected Map<Class<?>, Map<String, String>> setRegexParameters(Class<?> clazz,
			Map<String, String> mapOfClassFieldAndRegex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
