package by.htp.itacademy.processor;

import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import javax.lang.model.element.Element;

import javax.tools.*;

import by.htp.itacademy.annotation.Complexity;

@SupportedAnnotationTypes(value = ComplexityProcessor.ANNOTATION_TYPE)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ComplexityProcessor extends AbstractProcessor {

	
	public ComplexityProcessor() {
		super();
	}

	public static final String ANNOTATION_TYPE = "by.htp.itacademy.complexity_ann.annotation.processor.ComplexityProcessor";

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (Element elem : roundEnv.getElementsAnnotatedWith(Complexity.class)) {
			Complexity complexity = elem.getAnnotation(Complexity.class);
			String message = "annotation found in " + elem.getSimpleName() + " with complexity " + complexity.value();
			processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
		}
		return true;
	}
	
}
