package framework.webcore.util;

import static framework.util.FrameworkConstant.*;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.util.ObjectUtils;
import framework.webcore.bean.View;
import framework.webcore.http.HttpMethod;
import framework.webcore.http.HttpStatus;

public class WebUtil {

	public static void startHomePage(HttpServletRequest request, HttpServletResponse response, String reqMethod,
			String requestPath) {
		if (ObjectUtils.isEquals(HttpMethod.resolve("GET").name(), reqMethod) && requestPath.equals(SLASH)) {
			forwardRequest(request, response, new View<>(HOME_PAGE));
		}
	}
	
	public static void sendRedirectStatus(HttpServletRequest request, HttpServletResponse response, HttpStatus httpStatus, String viewPath) {
		response.setStatus(httpStatus.value());
		response.setHeader("Location", request.getContextPath().concat(viewPath));
	}

	public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, View<?> view) {
		try {
			response.sendRedirect(request.getContextPath().concat(view.getPath()));
		} catch (IOException e) {
			throw new RuntimeException(view.getPath(), e);
		}
	}

	public static void forwardRequest(HttpServletRequest request, HttpServletResponse response, View<?> view) {
		try {
			request.getRequestDispatcher(view.getPath()).forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(view.getPath(), e);
		}
	}

	public static void sendError(HttpServletResponse response, int code, String errorMsg) {
		try {
			response.sendError(code, errorMsg);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
