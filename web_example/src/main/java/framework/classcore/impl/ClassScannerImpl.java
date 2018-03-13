package framework.classcore.impl;

import java.lang.annotation.Annotation;
import java.util.List;

import framework.classcore.AnnotationClassTemplate;
import framework.classcore.ClassScanner;
import framework.classcore.ClassTemplate;
import framework.classcore.SuperClassTemplate;

public class ClassScannerImpl implements ClassScanner {

	@Override
	public List<Class<?>> getClassList(String packageName) {
		return new ClassTemplate(packageName) {
            @Override
            protected boolean checkAddClass(Class<?> clz) {
                String clzName = clz.getName();
                return clzName.startsWith(packageName);
            }
        }.getFileList();
	}

	@Override
	public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
		return new SuperClassTemplate(packageName, superClass) {
            @Override
            protected boolean checkAddClass(Class<?> clz) {
                return superClass.isAssignableFrom(clz);
            }
        }.getFileList();
	}

	@Override
	public List<Class<?>> getClassListByAnnotation(String basePackage, Class<? extends Annotation> annotationClass) {
		return new AnnotationClassTemplate(basePackage, annotationClass) {
            @Override
            protected boolean checkAddClass(Class<?> clz) {
                return clz.isAnnotationPresent(annotationClass);
            }
        }.getFileList();
	}
	
}
