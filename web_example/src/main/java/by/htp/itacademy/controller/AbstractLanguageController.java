package by.htp.itacademy.controller;

import javax.servlet.http.HttpSession;

import static util.CarConstant.*;

public abstract class AbstractLanguageController {

	protected String fetchLanguage(HttpSession session) {
		String language = (String)  session.getAttribute(REQUEST_ACTION_LANGUAGE);
		if (!LANGLIST.contains(language)) {
			language = LANGUAGE_RU;
		}
		return language;
	}
}
