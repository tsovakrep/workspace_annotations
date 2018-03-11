package framework.classcore;

import java.lang.annotation.Annotation;
import java.util.List;

public interface ClassScanner {
    
	List<Class<?>> getClassList(String packageName);

    List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);

    List<Class<?>> getClassListByAnnotation(String basePackage, Class<? extends Annotation> annotationClass);
}
