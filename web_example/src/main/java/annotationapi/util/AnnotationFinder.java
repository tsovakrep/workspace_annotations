package annotationapi.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import annotationapi.util.exception.ClassFindException;

public class AnnotationFinder {

	private Map<Class<?>, List<Annotation>> annotationConteiner;
	private List<Annotation> listOfAnnotations;
	private Class<? extends Annotation> desirerAnnotation;

	public AnnotationFinder(Class<? extends Annotation> desirerAnnotation) {
		this.desirerAnnotation = desirerAnnotation;
		listOfAnnotations = new LinkedList<>();
		annotationConteiner = new ConcurrentHashMap<>();
	}

	public Map<Class<?>, List<Annotation>> getAnnotationConteiner() {
		return annotationConteiner;
	}

	public Annotation getAnnotation(Class<?> clazz, Class<?> annotation) {
		for (Annotation annot : listOfAnnotations) {
			if (annot.annotationType().getName().equals(annotation.getName()))
				return annot;
		}
		return null;
	}

	public void searchAnnotation(Class<?> clazz) throws ClassFindException {
		if (clazz == null) {
			throw new ClassFindException("The class cannot be null.");
		}

		annotClass(clazz);
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
}
