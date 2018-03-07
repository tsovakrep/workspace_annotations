package annotationapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import annotationapi.annotation.PathVariable;
import annotationapi.annotation.ReqBody;
import annotationapi.annotation.ReqParam;
import by.htp.itacademy.controller.AppServletDispatcher;
import chaincasttype.FacadeCast;

public class MethodDispatcher {

	private String pathVariableValue;

	public void callMethodDispatcher(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException,
			ServletException, IOException {

		ServletContext sc = request.getServletContext();
//		Set<String> pages = (Set<String>) sc.getAttribute("pages");
//		ServletRegistration jsp = sc.getServletRegistration("jsp");
//		for (String string : pages) {
//			jsp.addMapping(string);
//		}
//
//		ServletRegistration servlet1 = sc.getServletRegistration("dispatcher");
//		servlet1.addMapping("/welcome/Tsovak");

		for (Map.Entry<String, ? extends ServletRegistration> servlet : sc.getServletRegistrations().entrySet()) {
			ServletRegistration servletReg = servlet.getValue();
			// if (servletReg.getClassName().startsWith("by.htp")) {
			System.out.println();
//			if (AppServletDispatcher.class.getName().equals(servlet.getValue().getClassName())) {
//				servletReg.addMapping("/go/ts");
//				servletReg.addMapping("/welcome");
//				servletReg.addMapping("/welcome/tsovak");
//				servletReg.addMapping("/welcome/palakian");
//				servletReg.addMapping("/callMethod/ts");
//			}
			// }
			System.out.println(servlet.getKey() + " : " + servletReg.getClassName());
			for (String url : servletReg.getMappings()) {
				System.out.println("url mapping" + " : " + url);
			}
		}
		
		AnnotationFinder af = (AnnotationFinder) sc.getAttribute("annotationfinder");

		Map<String, ServletContainer> methodContainerMap = af.getMethodContainer();
		String uri = changeUri(request, methodContainerMap);
		System.out.println("uri: " + uri);
		ServletContainer scont = methodContainerMap.get(uri);
		if (scont != null) {
			Object[] parameters = null;
			try {
				parameters = getParametersForInvokeMethod(scont, request);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Object returnObject = scont.getMethod().invoke(scont.getServletClass().newInstance(), parameters);

			ResponseEntity<?> resEntity = (ResponseEntity<?>) returnObject;
			if (resEntity != null) {
				if (resEntity.getPage() != null) {
					RequestDispatcher rd = request.getRequestDispatcher(resEntity.getPage());
					rd.forward(request, response);
				}
			}
		} else {
			System.out.println("Servlet container is null");
		}
	}

	private Object[] getParametersForInvokeMethod(ServletContainer scont, HttpServletRequest request) throws Exception {
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
						methodParameters[j] = FacadeCast.getCastChain().getValue(params[j].getType(),
								requestParameterValue);
					} else {
						methodParameters[j] = FacadeCast.getCastChain().getValue(params[j].getType(),
								reqParam.defaultValue());
					}
				} else if (PathVariable.class.getTypeName().equals(map.get(arg).annotationType().getTypeName())) {
					methodParameters[j] = pathVariableValue;
				} else if (ReqBody.class.getTypeName().equals(map.get(arg).annotationType().getTypeName())) {
					Gson gson = new Gson();
					String jsonString = /* gson.toJson(new User("Tsovak Palakian", 29)); */getJsonString(request);
					methodParameters[j] = gson.fromJson(jsonString, params[j].getType());
				}
			} else if (HttpSession.class.getName().equals(params[j].getType().getName())) {
				methodParameters[j] = request.getSession();
			}
		}
		return methodParameters;
	}

	private String changeUri(HttpServletRequest request, Map<String, ServletContainer> methodContainerMap) {
		String uri = request.getRequestURI();

		if (uri.endsWith("/")) {
			int slashIndex = uri.lastIndexOf("/");
			uri = uri.substring(0, slashIndex);
			uri = uri.concat(request.getMethod());
			return uri;
		}
		for (Map.Entry<String, ServletContainer> meth : methodContainerMap.entrySet()) {

			if (request.getMethod().equals(meth.getValue().getHttpMethod().name())) {
				int index = meth.getKey().indexOf("{");
				if (index != -1) {
					String mehtSub = meth.getKey().substring(0, index);
					if (uri.contains(mehtSub)) {
						pathVariableValue = uri.substring(index);
						return meth.getKey();
					}
				}
				if (meth.getKey().contains(uri)) {
					return uri.concat(request.getMethod());
				}
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
