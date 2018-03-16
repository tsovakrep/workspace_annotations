package framework.webcore.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Validation {
	
	protected static final Map<Class<?>, Map<String, String>> REGEX_PARAMETERS = new ConcurrentHashMap<>();

	public static Map<Class<?>, Map<String, String>> getRegexParameters() {
		return REGEX_PARAMETERS;
	}
	
	protected abstract Class<?> setRegexKey();
	protected abstract Map<String, String> setRegexValue();
}
