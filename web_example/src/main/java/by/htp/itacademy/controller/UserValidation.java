package by.htp.itacademy.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import framework.webcore.annotation.validation.Component;
import framework.webcore.validation.Validation;

@Component
public class UserValidation extends Validation {

	@Override
	protected Map<Class<?>, Map<String, String>> setRegexParameters(Class<?> clazz,
			Map<String, String> mapOfClassFieldAndRegex) {
		
		Map<String, String> user_regex_parameters = new ConcurrentHashMap<String, String>();
		
		user_regex_parameters.put("login", "[A-z0-9]{3,20}");
		user_regex_parameters.put("password", "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*]).{8,100})");
		user_regex_parameters.put("name", "[A-Z]{1}[a-z]{0,100}");
		user_regex_parameters.put("surname", "[A-Z]{1}[a-z]{0,100}");
		user_regex_parameters.put("email", "^[a-zA-Z0-9.,_%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4}$");
		user_regex_parameters.put("passport", "[A-Z]{2}[0-9]{7}");
		user_regex_parameters.put("phoneNumber", "[\\+]{1}[0-9]{2,4}[0-9]+");
		
		REGEX_PARAMETERS.put(User.class, user_regex_parameters);
		return REGEX_PARAMETERS;
	}
}
