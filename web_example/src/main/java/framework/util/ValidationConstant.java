package framework.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ValidationConstant {
	
	public static final Map<String, String> REGEX_PARAMETERS = new ConcurrentHashMap<String, String>();

	static {
		REGEX_PARAMETERS.put("login", "[A-z0-9]{3,20}");
		REGEX_PARAMETERS.put("password", "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*]).{8,100})");
		REGEX_PARAMETERS.put("name", "[A-Z]{1}[a-z]{0,100}");
		REGEX_PARAMETERS.put("surname", "[A-Z]{1}[a-z]{0,100}");
		REGEX_PARAMETERS.put("email", "^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4}$");
		REGEX_PARAMETERS.put("passport", "[A-Z]{2}[0-9]{7}");
		REGEX_PARAMETERS.put("phoneNumber", "[\\+]{1}[0-9]{2,4}[0-9]+");
	}
}
