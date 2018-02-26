package by.htp.itacademy.controller.app;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ControllerFactory factory;

	public BaseServlet() {
		super();
		factory = new ControllerFactory();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {       
		try {
			String name = getControllerName(request);
			if (name == null)
				name = "index";
			Controller controller = factory.create(name);
			Controller.setHttp(request, response, getServletContext());
			String method = getMethodName(request);
			if (method == null)
				method = "index";
			callMethod(controller, method, request, response);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public String getControllerName(HttpServletRequest request) {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("request.getRequestURI(): " + request.getRequestURI());
		System.out.println("path: " + path);
		System.out.println("request.getContextPath(): " + request.getContextPath());
		
		if (path == null || "".equals(path) || "/".equals(path))
			return null;

		String controller = path.substring(1, path.lastIndexOf("/"));
		System.out.println("controller: " + controller);
		if (controller != null && !"".equals(controller))
			return controller;

		return null;
	}

	private String getMethodName(HttpServletRequest request) {
		String method = request.getRequestURI().substring(request.getContextPath().length());
		if (method != null && !"".equals(method) && !"/".equals(method))
			return method.substring(method.lastIndexOf("/") + 1, method.length());

		return null;
	}

	private void callMethod(Controller controller, String methodName, HttpServletRequest request,
			HttpServletResponse response) throws NoSuchMethodException, RuntimeException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		for (Method method : controller.getClass().getMethods()) {
			if (method.getName().equalsIgnoreCase(methodName)) {
				Object[] params = new Object[method.getParameterTypes().length];
				Annotation[][] parameterAnnotations = method.getParameterAnnotations();
				Class[] parameterTypes = method.getParameterTypes();
				int i = 0;
				for (Annotation[] annotations : parameterAnnotations) {
					Class parameterType = parameterTypes[i];
					for (Annotation annotation : annotations) {
						if (annotation instanceof Param) {
							Param myAnnotation = (Param) annotation;
							Object value = null;
							if (parameterType.getSimpleName().equals("Integer")) {
								try {
									value = Integer.parseInt(request.getParameter(myAnnotation.name()));
								} catch (NumberFormatException e) {
									value = null;
								}
							} else {
								value = request.getParameter(myAnnotation.name());
							}
							params[i++] = value;

							System.out.println("param: " + parameterType.getSimpleName());
							System.out.println("name : " + myAnnotation.name());
							System.out.println("value: " + value);
						}
					}
				}
				controller.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(controller,
						params);
			}
		}
	}
}
