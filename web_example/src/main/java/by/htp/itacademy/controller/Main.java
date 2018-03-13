package by.htp.itacademy.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		String requestUrl = "/user/getUserInfo/{uid}/{aid}/{adf}";
		// System.out.println(requestPath.matches(".+\\{\\w+}.*"));
		requestUrl = requestUrl.replaceAll("\\{\\w+\\}", "(\\\\w+\\)");
		// System.out.println(requestPath);

		// requestPath = replaceAll(requestPath, "\\{\\w+\\}", "(\\\\w+)");
		// System.out.println(requestPath);

		String requestPath = "/user/getUserInfo/333/xxxx/adf";
		Matcher matcher = Pattern.compile(requestUrl).matcher(requestPath);
		System.out.println(matcher.matches());

		for (int i = 1; i <= matcher.groupCount(); i++) {
			// System.out.println("matcher counter :" + matcher.groupCount());
			String group = matcher.group(i);
			System.out.println(group);
		}
	}
}
