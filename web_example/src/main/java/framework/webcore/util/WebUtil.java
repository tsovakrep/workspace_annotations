package framework.webcore.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import framework.util.FrameworkConstant;
import framework.util.ObjectUtils;
import framework.webcore.annotation.PathVariable;
import framework.webcore.annotation.ReqBody;
import framework.webcore.annotation.ReqParam;
import framework.webcore.bean.Handler;
import framework.webcore.exception.InitializationException;
import framework.webcore.util.chaincasttype.FacadeCast;

public class WebUtil {
	
    public static String getRequestUrl(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (ObjectUtils.isNotEmptyString(pathInfo)) {
        	return servletPath;
        }
        return servletPath + pathInfo;
    }
    
    public static void redirectRequest(String pagePath, HttpServletRequest request, HttpServletResponse response) {
        try {
        	System.out.println(request.getContextPath()+pagePath);
            response.sendRedirect(request.getContextPath() + pagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String viewPath) {
        try {
            response.sendRedirect(request.getContextPath() + viewPath);
        } catch (IOException e) {
            throw new RuntimeException(viewPath, e);
        }
    }

    public static void forwardRequest(HttpServletRequest request, HttpServletResponse response, String forwardPath) {
        try {
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(forwardPath, e);
        }
    }

    public static void sendError(HttpServletResponse response, int code, String errorMsg) {
        try {
            response.sendError(code, errorMsg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static void writeJSON(HttpServletRequest request, HttpServletResponse response, String result) {
        try {
            response.setCharacterEncoding(FrameworkConstant.ENCODING);
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(result);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	public static List<Object> createPathParamList(HttpServletRequest request, Class<?>[] parameterTypes,
			Annotation[] annotations, Handler handler) {
		List<Object> paramList = new ArrayList<Object>();
		for (int j = 0; j < parameterTypes.length; j++) {

			ReqParam reqParam = (ReqParam) getAnnotation(parameterTypes, annotations);
			String requestParameterValue = request.getParameter(reqParam.value());
			try {
				if (requestParameterValue != null) {
					paramList.add(FacadeCast.getCastChain().getValue(parameterTypes[j], requestParameterValue));
				} else {
					paramList.add(FacadeCast.getCastChain().getValue(parameterTypes[j], reqParam.defaultValue()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (PathVariable.class.getTypeName()
					.equals(getAnnotation(parameterTypes, annotations).getClass().getTypeName())) {
				String pathVariableValue = getPathVariableFromAnnotation(request);
				if (ObjectUtils.isNotEmptyString(pathVariableValue)) {
					paramList.add(pathVariableValue);
				}
			}

			if (ReqBody.class.getTypeName()
					.equals(getAnnotation(parameterTypes, annotations).getClass().getTypeName())) {
				String jsonString = /* gson.toJson(new User("Tsovak Palakian", 29)); */JSONUtil.getJsonString(request);
				paramList.add(JSONUtil.fromJSON(jsonString, parameterTypes.getClass()));
			}

			if (HttpSession.class.getName().equals(parameterTypes[j].getName())) {
				paramList.add(request.getSession());
			}
		}
		return paramList;
	}
	
	@SuppressWarnings("unused")
	private static Annotation getAnnotation(Class<?>[] parameterTypes, Annotation[] annotations) {
		for (int i = 0; i < parameterTypes.length; i++) {
			for (int j = 0; j < annotations.length; j++) {
				if (parameterTypes[i].getName().equals(annotations[j].annotationType().getName())) {
					return annotations[j];
				} else {
					throw new InitializationException("Annotation is not found!");
				}
			}
		}
		return null;
	}

	private static String getPathVariableFromAnnotation(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String pathVariableValue = null;
		int index = uri.indexOf("{");
		if (index != -1) {
			String mehtSub = uri.substring(0, index);
			if (uri.contains(mehtSub)) {
				pathVariableValue = uri.substring(index);
			}
		}
		return pathVariableValue;
	}
}
