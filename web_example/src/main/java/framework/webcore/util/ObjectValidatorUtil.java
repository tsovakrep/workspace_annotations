package framework.webcore.util;

import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.NoSuchPaddingException;

import framework.util.ObjectUtils;
import framework.webcore.annotation.coder.Crypt;
import framework.webcore.annotation.validation.Component;
import framework.webcore.annotation.validation.Validate;
import framework.webcore.coder.CipherMachine;
import framework.webcore.coder.Encoder;
import framework.webcore.exception.IllegalParameterException;
import framework.webcore.exception.InitializationException;
import framework.webcore.exception.ValidationException;
import framework.webcore.helper.BeanHelper;
import framework.webcore.validation.Validation;

public class ObjectValidatorUtil {

	public static void validator(Object obj, Map<String, String> regexParameters) throws Exception {
		for (Field field : MethodUtil.getDeclaredFields(obj)) {
			if (field.isAnnotationPresent(Validate.class)) {
				field.setAccessible(true);
				checkObject(field.get(obj), regexParameters);
			}
		}
	}

	private static void checkObject(Object obj, Map<String, String> regexParameters)
			throws ValidationException, IllegalArgumentException, IllegalAccessException, 
			IllegalParameterException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {

		Map<String, Object> fieldNameAndValue = fieldsValuesFromEntity(obj);

		for (Entry<String, String> regex : regexParameters.entrySet()) {
			for (Entry<String, Object> value : fieldNameAndValue.entrySet()) {
				if (ObjectUtils.isNotNullObject(value.getValue())) {
					if (regex.getKey().contains((value.getKey()))) {
						Pattern pattern = Pattern.compile(regex.getValue());
						Matcher matcher = pattern.matcher(value.getValue().toString());
						if (!matcher.matches()) {
							throw new ValidationException();
						}
					}
				}
			}
		}
	}

	private static Map<String, Object> fieldsValuesFromEntity(Object obj)
			throws IllegalParameterException, IllegalArgumentException, IllegalAccessException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException {

		if (obj == null) {
			throw new ValidationException();
		}

		Map<String, Object> fieldNameAndValue = new ConcurrentHashMap<String, Object>();

		Field[] fields = MethodUtil.getDeclaredFields(obj);

		for (Field field : fields) {
			field.setAccessible(true);
			if (field.get(obj) != null) {
				if (field.isAnnotationPresent(Crypt.class)) {
					String encryptedString = encrypt(field, obj);
					fieldNameAndValue.put(field.getName(), encryptedString);
				} else {
					fieldNameAndValue.put(field.getName(), field.get(obj));
				}
			}
		}
		return fieldNameAndValue;
	}

	private static String encrypt(Field field, Object obj) throws IllegalArgumentException, IllegalAccessException,
			InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		
		String encryptedString = null;
		CipherMachine cipherMachine = new Encoder();
		cipherMachine.init();
		if (ObjectUtils.isEqueals(field.get(obj), String.class)) {
			encryptedString = cipherMachine.encrypt((String) field.get(obj));
		}
		return encryptedString;
	}

	public static Map<String, String> mapRegex(Object object) {
		Map<String, String> objectRegex = null;
		try {
			Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
			if (ObjectUtils.isNotEmptyMap(beanMap)) {
				for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
					checkComponentAnnotationAndGetMapOfRegex(object, entry, objectRegex);
				}
			}
		} catch (Exception e) {
			throw new InitializationException("Component error's");
		}
		return objectRegex;
	}

	private static void checkComponentAnnotationAndGetMapOfRegex(Object object, Entry<Class<?>, Object> entry,
			Map<String, String> objectRegex) {
		Class<?> clz = entry.getKey();
		Object beanInstance = entry.getValue();
		if (clz.isAnnotationPresent(Component.class)) {
			if (ObjectUtils.isEqueals(beanInstance, Validation.class)) {
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
}
