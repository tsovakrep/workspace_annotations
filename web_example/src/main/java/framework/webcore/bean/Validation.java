package framework.webcore.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Validation {
	
	protected final Map<Class<?>, Map<String, String>> REGEX_PARAMETERS = new ConcurrentHashMap<>();

	public Map<Class<?>, Map<String, String>> getRegexParameters() {
		return REGEX_PARAMETERS;
	}
	
	protected abstract Map<Class<?>, Map<String, String>> setRegexParameters(Class<?> clazz, Map<String, String> mapOfClassFieldAndRegex);
	
}
