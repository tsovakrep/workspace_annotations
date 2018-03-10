package by.htp.itacademy.framework.classcore.impl;

import java.lang.annotation.Annotation;
import java.util.List;

import by.htp.itacademy.framework.classcore.AnnotationClassTemplate;
import by.htp.itacademy.framework.classcore.ClassScanner;
import by.htp.itacademy.framework.classcore.ClassTemplate;
import by.htp.itacademy.framework.classcore.SuperClassTemplate;

public class ClassScannerImpl implements ClassScanner {

	@Override
	public List<Class<?>> getClassList(String packageName) {
		return new ClassTemplate(packageName) {
            @Override
            protected boolean checkAddClass(Class<?> clz) {
                String clzName = clz.getName();
                return clzName.startsWith(packageName);
            }
        }.getClassList();
	}

	@Override
	public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
		return new SuperClassTemplate(packageName, superClass) {
            @Override
            protected boolean checkAddClass(Class<?> clz) {
                return superClass.isAssignableFrom(clz);
            }
        }.getClassList();
	}

	@Override
	public List<Class<?>> getClassListByAnnotation(String basePackage, Class<? extends Annotation> annotationClass) {
		return new AnnotationClassTemplate(basePackage, annotationClass) {
            @Override
            protected boolean checkAddClass(Class<?> clz) {
                return clz.isAnnotationPresent(annotationClass);
            }
        }.getClassList();
	}
	
}
