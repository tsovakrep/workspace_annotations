package by.htp.itacademy.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import framework.webcore.annotation.validation.Component;
import framework.webcore.bean.Validation;

@Component
public class ValidationConstant extends Validation {
	
	private static final Map<String, String> USER_REGEX_PARAMETERS = new ConcurrentHashMap<String, String>();

	static {
		USER_REGEX_PARAMETERS.put("login", "[A-z0-9]{3,20}");
		USER_REGEX_PARAMETERS.put("password", "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*]).{8,100})");
		USER_REGEX_PARAMETERS.put("name", "[A-Z]{1}[a-z]{0,100}");
		USER_REGEX_PARAMETERS.put("surname", "[A-Z]{1}[a-z]{0,100}");
		USER_REGEX_PARAMETERS.put("email", "^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4}$");
		USER_REGEX_PARAMETERS.put("passport", "[A-Z]{2}[0-9]{7}");
		USER_REGEX_PARAMETERS.put("phoneNumber", "[\\+]{1}[0-9]{2,4}[0-9]+");
	}
	
	static {
		REGEX_PARAMETERS.put(User.class, USER_REGEX_PARAMETERS);
	}
}
