package annotationapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
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

public class MethodDispatcher {
	
	private boolean isPathVariable = false;
	private String pathVariableValue;
	
	private static final Gson gson = new Gson();

	public void callMethod(HttpServletRequest request, HttpServletResponse response) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		ServletContext sc = request.getServletContext();
		AnnotationFinder af = (AnnotationFinder) sc.getAttribute("annotationfinder");
		HttpSession session = request.getSession();
		Map<String, ServletContainer> methodContainerMap = af.getMethodContainer();

		String uri = request.getRequestURI();
		
		uri = changeUri(uri, methodContainerMap);
		
		System.out.println("uri: " + uri);
		System.out.println(methodContainerMap);
		ServletContainer scont = methodContainerMap.get(uri);
		Object[] parameters = null;
		try {
			parameters = getParametersForInvokeMethod(scont, request);
		} catch (JsonSyntaxException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scont.getMethod().invoke(scont.getServletClass().newInstance(), parameters);

		String lang = session.getAttribute("language").toString();
		System.out.println(lang);
	}
	
	private Object[] getParametersForInvokeMethod(ServletContainer scont, HttpServletRequest request) 
			throws JsonSyntaxException, ClassNotFoundException {
		Map<String, Annotation> map = scont.getMapAnnotForMethodParams();
		Class<?>[] paramType = scont.getParameterTypes();
		Object[] methodParameters = new Object[paramType.length];
		for (int j = 0; j < paramType.length; j++) {
			String arg = String.valueOf("arg" + j);
			if (map.containsKey(String.valueOf(arg))) {
				if (ReqParam.class.getTypeName().equals(map.get(arg).annotationType().getTypeName())) {
					ReqParam reqParam = (ReqParam) map.get(arg);
					methodParameters[j] = reqParam.value();
				} else if (PathVariable.class.getTypeName().equals(map.get(arg).annotationType().getTypeName())) {
					methodParameters[j] = pathVariableValue;
				} else if (ReqBody.class.getTypeName().equals(map.get(arg).annotationType().getTypeName())) {
					String jsonStr = getJsonString(request);
					methodParameters[j] = gson.fromJson(jsonStr, Class.forName(paramType[j].getTypeName()));
				}
			} else if (HttpSession.class.getName().equals(paramType[j].getName())) {
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
				isPathVariable = true;
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
