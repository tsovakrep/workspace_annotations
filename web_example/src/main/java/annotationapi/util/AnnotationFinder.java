package annotationapi.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

import annotationapi.annotation.Controller;
import annotationapi.annotation.DeleteMapping;
import annotationapi.annotation.GetMapping;
import annotationapi.annotation.Mapping;
import annotationapi.annotation.PostMapping;
import annotationapi.annotation.PutMapping;

public class AnnotationFinder {

	private Map<Class<?>, List<Annotation>> annotationConteiner;
	private ServletContext context;
	private List<Annotation> listOfAnnotations;
	private Class<? extends Annotation> desirerAnnotation;
	private static final Map<String, MethodContainer> METHOD_CONTAINER = new ConcurrentHashMap<>();;

	public AnnotationFinder(Class<? extends Annotation> desirerAnnotation, ServletContext context) {
		this.desirerAnnotation = desirerAnnotation;
		this.context = context;
		listOfAnnotations = new LinkedList<>();
		annotationConteiner = new ConcurrentHashMap<>();
	}

	public Map<Class<?>, List<Annotation>> getAnnotationConteiner() {
		return annotationConteiner;
	}
	
	public Map<String, MethodContainer> getMethodContainer() {
		return METHOD_CONTAINER;
	}

	public Annotation getAnnotation(Class<?> clazz, Class<?> annotation) {
		for (Annotation annot : listOfAnnotations) {
			if (annot.annotationType().getName().equals(annotation.getName()))
				return annot;
		}
		return null;
	}

	public void searchAnnotation(Class<?> clazz) throws ClassNotFoundException {
		if (clazz == null) {
			throw new ClassNotFoundException("The class cannot be null.");
		}

		annotClass(clazz);
	}
	
	public void fillingMethodContainer(Class<?> clazz) {
		getMethodContainer(clazz);
	}

	private void annotClass(Class<?> clazz) {
		boolean isAnnotation = false;
		Annotation[] classAnnotation = clazz.getAnnotations();
		for (Annotation annotation : classAnnotation) {
			if (this.desirerAnnotation.getName().equals(annotation.annotationType().getName())) {
				listOfAnnotations.add(annotation);	
				annotField(clazz);
				annotMethod(clazz);
				methodParameterAnnotation(clazz);
				isAnnotation = true;
			}
		}
		if (isAnnotation) {
			annotationConteiner.put(clazz, listOfAnnotations);
		}
	}

	private void annotMethod(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			Annotation[] mehtodAnnotations = method.getAnnotations();
			for (Annotation annotation : mehtodAnnotations) {
				listOfAnnotations.add(annotation);
			}
		}
	}

	private void methodParameterAnnotation(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			for (Annotation[] annotations : parameterAnnotations) {
				for (Annotation annotation : annotations) {
					listOfAnnotations.add(annotation);
				}
			}
		}
	}

	private void annotField(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] fieldAnnotations = field.getAnnotations();
			for (Annotation annotation : fieldAnnotations) {
				listOfAnnotations.add(annotation);
			}
		}
	}
	
	private Map<String, MethodContainer> getMethodContainer(Class<?> clazz) {
		String contextPath = context.getContextPath().concat("/");
		String mappingValue = null;
		
		if (clazz.isAnnotationPresent(desirerAnnotation)) {
			Annotation[] classAnnotations = clazz.getAnnotations();
			for (Annotation classAnnotation : classAnnotations) {
				if (Mapping.class.getName().equals(classAnnotation.annotationType().getName())) {
					Mapping mapping = (Mapping) classAnnotation;
					mappingValue = contextPath.concat(mapping.value().concat("/"));
				}
			}
			for (Annotation classAnnotation : classAnnotations) {
				if (Controller.class.getName().equals(classAnnotation.annotationType().getName())) {
					Method[] methods = clazz.getMethods();
					for (Method method : methods) {
						Annotation[] mehtodAnnotations = method.getAnnotations();
						for (Annotation methtodAnnotation : mehtodAnnotations) {
							if (GetMapping.class.getName().equals(methtodAnnotation.annotationType().getName())) {
								GetMapping getMapping = (GetMapping) methtodAnnotation;
								String getMappingValue = getMapping.value();
								String url = mappingValue.concat(getMappingValue);
								MethodContainer mc = new MethodContainer(url, method, HttpMethod.GET);
								METHOD_CONTAINER.put(url, mc);
							}
							if(PostMapping.class.getName().equals(methtodAnnotation.annotationType().getName())) {
								PostMapping postMapping = (PostMapping) methtodAnnotation;
								String postMappingValue = postMapping.value();
								String url = mappingValue.concat(postMappingValue);
								MethodContainer mc = new MethodContainer(url, method, HttpMethod.POST);
								METHOD_CONTAINER.put(url, mc);
							}
							if(PutMapping.class.getName().equals(methtodAnnotation.annotationType().getName())) {
								PutMapping putMapping = (PutMapping) methtodAnnotation;
								String putMappingValue = putMapping.value();
								String url = mappingValue.concat(putMappingValue);
								MethodContainer mc = new MethodContainer(url, method, HttpMethod.PUT);
								METHOD_CONTAINER.put(url, mc);
							}
							if(DeleteMapping.class.getName().equals(methtodAnnotation.annotationType().getName())) {
								DeleteMapping deleteMapping = (DeleteMapping) methtodAnnotation;
								String deleteMappingValue = deleteMapping.value();
								String url = mappingValue.concat(deleteMappingValue);
								MethodContainer mc = new MethodContainer(url, method, HttpMethod.DELETE);
								METHOD_CONTAINER.put(url, mc);
							}
						}
					}
				}
			}
		}
		return METHOD_CONTAINER;
	}
}
