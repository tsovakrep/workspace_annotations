package by.htp.itacademy.framework.classcore;

import java.lang.annotation.Annotation;
import java.util.List;

import by.htp.itacademy.framework.util.FrameworkConstant;
import by.htp.itacademy.framework.webcore.util.InstanceFactory;

public class ClassHelper {
	
	private static final ClassScanner classScanner = InstanceFactory.getClassScanner();
	
	public static List<Class<?>> getBasePackageClassList() {
        return classScanner.getClassList(FrameworkConstant.CLASSES_PACKAGE);
    }

    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {
        return classScanner.getClassListBySuper(FrameworkConstant.CLASSES_PACKAGE, superClass);
    }

    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass) {
        return classScanner.getClassListByAnnotation(FrameworkConstant.CLASSES_PACKAGE, annotationClass);
    }
}
