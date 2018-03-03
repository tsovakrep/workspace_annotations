package annotationapi.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServletContainer {
	
	private String url;
	private Class<?> servletClassName;
	private Method method;
	private Object[] methodParameterTypes;
	private HttpMethod httpMethod;
	private Map<String, Annotation> mapOfAnnotations;
	private Class<?>[] parameterTypes;

	public ServletContainer(String url, Class<?> servletClassName, Method method, HttpMethod httpMethod) {
		this.url = url;
		this.servletClassName = servletClassName;
		this.method = method;
		this.httpMethod = httpMethod;
		this.methodParameterTypes = new Object[method.getParameterTypes().length];
		this.parameterTypes = method.getParameterTypes();
		this.mapOfAnnotations = mapOfAnnotations();
	}
	
	public String getUrl() {
		return url;
	}

	public Method getMethod() {
		return method;
	}

	public Object[] getMethodParameterTypes() {
		return methodParameterTypes;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public Map<String, Annotation> mapAnnotationForMethodParameters() {
		return mapOfAnnotations;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}
	
	public Class<?> getServletClassName() {
		return servletClassName;
	}

	private Map<String, Annotation> mapOfAnnotations() {
		mapOfAnnotations = new ConcurrentHashMap<>();
		Parameter[] parameters = method.getParameters();
		for (Parameter parameter : parameters) {
			Annotation[] annotations = parameter.getAnnotations();
			for (Annotation annotation : annotations) {
				mapOfAnnotations.put(parameter.getName(), annotation);
			}
		}
		return mapOfAnnotations;
	}
}
