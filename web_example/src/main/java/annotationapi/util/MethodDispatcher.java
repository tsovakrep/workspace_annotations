package annotationapi.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import annotationapi.annotation.PathVariable;
import annotationapi.annotation.ReqBody;
import annotationapi.annotation.ReqParam;
import by.htp.itacademy.controller.DefaultController;

public class MethodDispatcher {
	
	private boolean isPathVariable = false;
	private String pathVariableValue;

	public void callMethod(HttpServletRequest request, HttpServletResponse response) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		ServletContext sc = request.getServletContext();
//		AnnotationFinder af = (AnnotationFinder) sc.getAttribute("annotationfinder");
//		HttpSession session = request.getSession();
//		Map<String, ServletContainer> methodContainerMap = af.getMethodContainer();
//
//		String uri = request.getRequestURI();
//		
//		uri = changeUri(uri, methodContainerMap);
		
//		System.out.println("uri: " + uri);
//		System.out.println(methodContainerMap);
//		ServletContainer scont = methodContainerMap.get(uri);
//		Object[] parameters = new Object[scont.getParameterTypes().length];
//		
//		getParametersForInvokeMethod(scont);
//		scont.getMethod().invoke(scont.getServletClass().newInstance(), session);
//		String lang = session.getAttribute("language").toString();
//		System.out.println(lang);
//		System.out.println("mc: " + mc);
//		System.out.println(mc.getUrl() + " : " + mc.getMethod().getName());

		System.out.println("request.getRequestURI(): " + request.getRequestURI());
	}
	
	private Object[] getParametersForInvokeMethod(ServletContainer scont, HttpServletRequest request) {
		int i = 0;
		Map<String, Annotation> map = scont.getMapAnnotForMethodParams();
		Class<?>[] paramType = scont.getParameterTypes();
		Object[] parameters = new Object[paramType.length];
		for (int j = 0; j < paramType.length; j++) {
			String arg = String.valueOf("arg" + j);
			if (map.containsKey(String.valueOf(arg))) {
				if (ReqParam.class.getTypeName().equals(map.get(arg).getClass().getTypeName())) {
					ReqParam reqParam = (ReqParam) map.get(arg);
					parameters[j] = reqParam.value();
				} else if (PathVariable.class.getTypeName().equals(map.get(arg).getClass().getTypeName())) {
					parameters[j] = pathVariableValue;
				} else if (ReqBody.class.getTypeName().equals(map.get(arg).getClass().getTypeName())) {
					//parameters[j] = request.
				}
			}
		}
		return parameters;
	}
	
	private Object getParameter(Map.Entry<String, Annotation> keyAndValue) {
//		if (keyAndValue.getKey().endsWith(suffix)) {}
		return null;
	}

	private String changeUri(String uri, Map<String, ServletContainer> methodContainerMap) {
		
		for (Map.Entry<String, ServletContainer> meth : methodContainerMap.entrySet()) {
			
			int index = meth.getKey().indexOf("{");
			if (index == -1) {
				continue;
			}
			
			String mehtSub = meth.getKey().substring(0, index);
			if (uri.contains(mehtSub)) {
				isPathVariable = true;
				pathVariableValue = uri.substring(index);
				System.out.println("pathVariableValue: " + pathVariableValue);
				return meth.getKey();
			}
		}
		return uri;
	}
}
