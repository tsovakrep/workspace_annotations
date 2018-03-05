package annotationapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import annotationapi.annotation.PathVariable;
import annotationapi.annotation.ReqBody;
import annotationapi.annotation.ReqParam;
import chaincasttype.FacadeCast;

public class MethodDispatcher {
	
	private String pathVariableValue;
	
	private static final Gson gson = new Gson();

	public void callMethod(HttpServletRequest request, HttpServletResponse response) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		ServletContext sc = request.getServletContext();
		AnnotationFinder af = (AnnotationFinder) sc.getAttribute("annotationfinder");
		Map<String, ServletContainer> methodContainerMap = af.getMethodContainer();

		String uri = request.getRequestURI();
		
		uri = changeUri(uri, methodContainerMap);
		
		System.out.println("uri: " + uri);
		System.out.println(methodContainerMap);
		ServletContainer scont = methodContainerMap.get(uri);
		Object[] parameters = null;
		try {
			parameters = getParametersForInvokeMethod(scont, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		scont.getMethod().invoke(scont.getServletClass().newInstance(), parameters);
	}
	
	private Object[] getParametersForInvokeMethod(ServletContainer scont, HttpServletRequest request) 
			throws Exception {
		Map<String, Annotation> map = scont.getMapAnnotForMethodParams();
		Parameter[] params = scont.getParameters();
		Object[] methodParameters = new Object[params.length];
		for (int j = 0; j < params.length; j++) {
			String arg = String.valueOf("arg" + j);
			if (map.containsKey(String.valueOf(arg))) {
				if (ReqParam.class.getTypeName().equals(map.get(arg).annotationType().getTypeName())) {
					ReqParam reqParam = (ReqParam) map.get(arg);
					String requestParameterValue = request.getParameter(reqParam.value());
					if (requestParameterValue != null) {
						methodParameters[j] = FacadeCast.getCastChain().getValue(params[j].getType(), request.getParameter(reqParam.value()));
					}
				} else if (PathVariable.class.getTypeName().equals(map.get(arg).annotationType().getTypeName())) {
					methodParameters[j] = pathVariableValue;
				} else if (ReqBody.class.getTypeName().equals(map.get(arg).annotationType().getTypeName())) {
					String jsonString = gson.toJson(new User("Tsovak Palakian", 29));//getJsonString(request);
					methodParameters[j] = gson.fromJson(jsonString, params[j].getType());
				}
			} else if (HttpSession.class.getName().equals(params[j].getType().getName())) {
				methodParameters[j] = request.getSession();
			}
		}
		return methodParameters;
	}
	
	private String changeUri(String uri, Map<String, ServletContainer> methodContainerMap) {
		
		for (Map.Entry<String, ServletContainer> meth : methodContainerMap.entrySet()) {
			
			int index = meth.getKey().indexOf("{");
			if (index == -1) {
				continue;
			}
			
			String mehtSub = meth.getKey().substring(0, index);
			if (uri.contains(mehtSub)) {
				pathVariableValue = uri.substring(index);
				System.out.println("pathVariableValue: " + pathVariableValue);
				return meth.getKey();
			}
		}
		return uri;
	}
	
	private String getJsonString(HttpServletRequest request) {
		InputStream body = null;
		StringBuilder buf = new StringBuilder(512);
		try {
			body = request.getInputStream();
			
			int b;
			while ((b = body.read()) != -1) {
				buf.append((char) b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
}
