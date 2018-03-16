package framework.webcore.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import framework.util.FrameworkConstant;
import framework.util.ObjectUtils;
import framework.util.chaincasttype.FacadeCast;
import framework.webcore.annotation.controller.parameter.PathVariable;
import framework.webcore.annotation.controller.parameter.ReqBody;
import framework.webcore.annotation.controller.parameter.ReqParam;
import framework.webcore.bean.Handler;
import framework.webcore.exception.InitializationException;
import framework.webcore.http.HttpStatus;

public class WebUtil {
	
    public static String getRequestUrl(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (ObjectUtils.isEmptyString(pathInfo)) {
        	return servletPath;
        }
        return servletPath + pathInfo;
    }

    public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String viewPath) {
        try {
            response.sendRedirect(request.getContextPath() + viewPath);
        } catch (IOException e) {
            throw new RuntimeException(viewPath, e);
        }
    }
    
    public static void sendRedirectWithHttpStatus(HttpServletRequest request, HttpServletResponse response, String path, HttpStatus status) {
        response.setStatus(status.value());
		response.setHeader("Locale", request.getContextPath() + path);
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
}
