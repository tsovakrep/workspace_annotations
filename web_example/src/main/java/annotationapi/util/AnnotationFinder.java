package annotationapi.util;

import java.lang.annotation.Annotation;
import java.util.List;

import annotationapi.util.exception.ClassFindException;

public class AnnotationFinder {
	
	private Class<?> clazz;
	
	private List<Class<?>> classContainer;
	
	public void annotationFind(Class<?> clazz) throws ClassFindException {
		if (clazz == null) {
			throw new ClassFindException("The class cannot be null.");
		}
		
		annot(clazz);
	}
	
	public void annot(Class<?> clazz) {
		Annotation[] classAnnotation = clazz.getAnnotations();
		System.out.println("annotation: " + classAnnotation);
	}
}
