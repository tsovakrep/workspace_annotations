package framework.webcore.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import framework.util.ObjectUtils;
import framework.webcore.annotation.validation.Component;
import framework.webcore.annotation.validation.Validate;
import framework.webcore.bean.Validation;
import framework.webcore.exception.IllegalParameterException;
import framework.webcore.exception.InitializationException;
import framework.webcore.helper.BeanHelper;

public class ValidationUtil {
	
	public static void validator(Object obj, Map<String, String> regexParameters) throws Exception {
		
		for (Field field : MethodUtil.getDeclaredFields(obj)) {
			if (field.isAnnotationPresent(Validate.class)) {
				field.setAccessible(true);
				checkObject(field.get(obj), regexParameters);
			}
		}
	}
	
	public static Map<String, String> mapRegex(Object object) {
		Map<String, String> objectRegex = null;
		try {
			Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
			if (ObjectUtils.isNotEmptyMap(beanMap)) {
				for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
					Class<?> clz = entry.getKey();
					Object beanInstance = entry.getValue();
					if (clz.isAnnotationPresent(Component.class)) {
						Validation validation = (Validation) beanInstance;
						Map<Class<?>, Map<String, String>> regexParameters = validation.getRegexParameters();
						for (Map.Entry<Class<?>, Map<String, String>> regexParameter : regexParameters.entrySet()) {
							if (ObjectUtils.isEqueals(object, regexParameter.getKey())) {
								objectRegex = regexParameter.getValue();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new InitializationException("Component error's");
		}
		return objectRegex;
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
