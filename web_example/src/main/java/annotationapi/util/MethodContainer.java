package annotationapi.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MethodContainer {
	
	private String url;
	private Method method;
	private Object[] methodParameterTypes;
	private HttpMethod httpMethod;
	private Map<String, Annotation> mapOfAnnotations;
	private Class<?>[] parameterTypes;

	public MethodContainer(String url, Method method, HttpMethod httpMethod) {
		this.url = url;
		this.method = method;
		this.httpMethod = httpMethod;
		this.methodParameterTypes = new Object[method.getParameterTypes().length];
		this.parameterTypes = method.getParameterTypes();
		this.mapOfAnnotations = mapOfAnnotations();
	}
	
	private Map<String, Annotation> mapOfAnnotations() {
		mapOfAnnotations = new ConcurrentHashMap<>();
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		for (Annotation[] annotations : parameterAnnotations) {
			for (Annotation annotation : annotations) {
				String annotName = annotation.annotationType().getName();
				mapOfAnnotations.put(annotName, annotation);
			}
		}
		return mapOfAnnotations;
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

	public Map<String, Annotation> getMapOfAnnotations() {
		return mapOfAnnotations;
	}

}
