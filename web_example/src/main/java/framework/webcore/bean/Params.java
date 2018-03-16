package framework.webcore.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;

@Data
public class Params {

	private Method actionMethod;
	@Getter
	private List<Class<?>> parameterTypes;
	@Getter
	private List<Parameter> parameters;
	@Getter
	private Map<Parameter, List<Annotation>> parameterAnnotations;

	public Params(Method actionMethod) {
		this.actionMethod = actionMethod;
		init();
	}

	public boolean containAnnotation(Parameter parameter, Class<? extends Annotation> annotationClass) {
		for (Annotation annotation : this.getParameterAnnotations().get(parameter)) {
			return annotation.annotationType().equals(annotationClass);
		}
		return false;
	}

	private void init() {
		this.parameters = new ArrayList<>();
		this.parameters = Arrays.asList(actionMethod.getParameters());

		this.parameterTypes = new ArrayList<>();
		this.parameterTypes = Arrays.asList(actionMethod.getParameterTypes());

		this.parameterAnnotations = new HashMap<>();

		for (Parameter parameter : parameters) {
			this.parameterAnnotations.put(parameter, Arrays.asList(parameter.getAnnotations()));
		}
	}
}
