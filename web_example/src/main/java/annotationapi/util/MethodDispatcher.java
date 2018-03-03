package annotationapi.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.itacademy.controller.DefaultController;

public class MethodDispatcher {
	
	private boolean isPathVariable = false;
	private String pathVariableValue;

	public void callMethod(HttpServletRequest request, HttpServletResponse response) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		ServletContext sc = request.getServletContext();
		AnnotationFinder af = (AnnotationFinder) sc.getAttribute("annotationfinder");
		HttpSession session = request.getSession();
		Map<String, ServletContainer> methodContainerMap = af.getMethodContainer();

		String uri = request.getRequestURI();
		
		uri = changeUri(uri, methodContainerMap);
		
//		System.out.println("uri: " + uri);
		System.out.println(methodContainerMap);
		ServletContainer scont = methodContainerMap.get(uri);
		System.out.println("map annotation: " + scont.mapAnnotationForMethodParameters());
//		Object[] parameters = new Object[scont.getParameterTypes().length];
//		
//		
//		scont.getMethod().invoke(scont.getServletClass().newInstance(), session);
//		String lang = session.getAttribute("language").toString();
//		System.out.println(lang);
//		System.out.println("mc: " + mc);
//		System.out.println(mc.getUrl() + " : " + mc.getMethod().getName());

		System.out.println("request.getRequestURI(): " + request.getRequestURI());
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
