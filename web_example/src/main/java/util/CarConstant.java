package util;

import java.util.Arrays;
import java.util.List;

public interface CarConstant {
	
	static final String REQUEST_ACTION_LANGUAGE = "language";
	
	static final String LANGUAGE_RU = "ru";
	static final String LANGUAGE_EN = "en";
	
	static final List<String> LANGLIST = Arrays.asList(LANGUAGE_RU, LANGUAGE_EN);
}
