package framework.webcore.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import framework.util.ObjectUtils;
import framework.webcore.ActionHelper;
import framework.webcore.HandlerMapping;
import framework.webcore.bean.Handler;
import framework.webcore.bean.Requester;
import framework.webcore.util.HttpMethod;

public class HandlerMappingImpl implements HandlerMapping {

	@Override
	public Handler getHandler(String requestPath, String requestMethod) {
		Map<Requester, Handler> actionMap = ActionHelper.getActionMap();

		Handler handler = null;
		if (ObjectUtils.isNotEmptyMap(actionMap)) {
			for (Map.Entry<Requester, Handler> entry : actionMap.entrySet()) {
				Requester requester = entry.getKey();

				String requestUrl = requester.getRequestUrls();
				HttpMethod reqMethod = requester.getRequestMethod();
				
				requestUrl = requestUrl.replaceAll("\\{\\w+\\}", "(\\\\w+\\)");
//				System.out.println("requestUrl: " + requestUrl);
//				System.out.println("requestPath: " + requestPath);
				
				Matcher matcher = Pattern.compile(requestUrl).matcher(requestPath);
//				System.out.println("matcher: " + matcher.matches());
				
				if (matcher.matches() && isMatchRequestMethod(reqMethod, requestMethod)) {
					handler = entry.getValue();
					if (handler != null) {
						handler.setRequestMatcher(matcher);
					}
					break;
				}
			}
		}
		return handler;
	}
	
	private boolean isMatchRequestMethod(HttpMethod reqMethod, String requestMethod) {
		boolean isMatch = false;
		if (reqMethod.name().equalsIgnoreCase(requestMethod)) {
			isMatch = true;
		}
		return isMatch;
	}

	@SuppressWarnings("unused")
	private static String replaceAll(String str, String regex, String replacement) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, replacement);
		}
		m.appendTail(sb);
		return sb.toString();
	}
}
