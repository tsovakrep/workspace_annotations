package framework.core;

import framework.mvc.util.InstanceFactory;

import java.lang.annotation.Annotation;
import java.util.List;

public class ClassHelper {
    private static final String BASE_PACKAGE = ConfigHelper.getString("smart.framework.app.base_package");

    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    public static List<Class<?>> getBasePackageClassList() {
        return classScanner.getClassList(BASE_PACKAGE);
    }

    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {
        return classScanner.getClassListBySuper(BASE_PACKAGE, superClass);
    }

    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass) {
        return classScanner.getClassListByAnnotation(BASE_PACKAGE, annotationClass);
    }
}
