package framework.webcore.validation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import framework.util.ObjectUtils;
import framework.webcore.annotation.validation.Component;
import framework.webcore.exception.InitializationException;
import framework.webcore.helper.BeanHelper;

public abstract class Validation {
	
	protected final Map<Class<?>, Map<String, String>> REGEX_PARAMETERS = new ConcurrentHashMap<>();

	public Map<Class<?>, Map<String, String>> getRegexParameters() {
		return REGEX_PARAMETERS;
	}
	
	protected Map<String, String> mapRegex(Object object) {
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
	
	protected abstract Map<Class<?>, Map<String, String>> setRegexParameters(Class<?> clazz, Map<String, String> mapOfClassFieldAndRegex);
	
}
