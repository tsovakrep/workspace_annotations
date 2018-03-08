package framework.mvc.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import framework.FrameworkConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebUtil {

    public static void writeJSON(HttpServletRequest request, HttpServletResponse response, String result) {
        try {
            response.setCharacterEncoding(FrameworkConstant.ENCODING);
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(result);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("??????json????????????", e);
        }
    }

    public static String getRequestUrl(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        return servletPath + pathInfo;
    }

    public static Map<String, Object> getRequestParamMap(HttpServletRequest request) {
        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        String requestMethod = request.getMethod();
        try {
            if ("PUT".equalsIgnoreCase(requestMethod) || "DELETE".equalsIgnoreCase(requestMethod)) {
                String queryString = CodecUtil.decodeUrl(IOUtils.toString(request.getInputStream(), FrameworkConstant.ENCODING));
                if (StringUtils.isNotEmpty(queryString)) {
                    String[] qsArray = queryString.split("&");
                    if (!ArrayUtils.isEmpty(qsArray)) {
                        for (String qs : qsArray) {
                            String[] strArray = StringUtils.split(qs, "=");
                            if (!ArrayUtils.isEmpty(strArray) && strArray.length == 2) {
                                String paramName = strArray[0];
                                String paramValue = strArray[1];
                                paramMap.put(paramName, paramValue);
                            }
                        }
                    }
                }
            } else {
                paramMap.putAll(request.getParameterMap());
            }
        } catch (Exception e) {
            throw new RuntimeException("????????????????????????", e);
        }
        return paramMap;
    }

    public static void redirectRequest(String pagePath, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + pagePath);
        } catch (IOException e) {
            throw new RuntimeException("???????????????", e);
        }
    }

    public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String viewPath) {
        try {
            response.sendRedirect(request.getContextPath() + viewPath);
        } catch (IOException e) {
            throw new RuntimeException("????????????" + viewPath + "??????", e);
        }
    }

    public static void forwardRequest(HttpServletRequest request, HttpServletResponse response, String forwardPath) {
        try {
            request.getRequestDispatcher(forwardPath).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("???????????????" + forwardPath + "??????", e);
        }
    }

    public static void sendError(HttpServletResponse response, int code, String errorMsg) {
        try {
            response.sendError(code, errorMsg);
        } catch (IOException e) {
            throw new RuntimeException("????????????????????????", e);
        }
    }
}
