package by.htp.itacademy.processor.immutable;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import by.htp.itacademy.annotation.Immutable;
import by.htp.itacademy.utility.log.DevLog;

@SupportedAnnotationTypes( "*" )
@SupportedSourceVersion( SourceVersion.RELEASE_8 )
public class GeneratingAnnotationProcessor extends AbstractProcessor {
	
	public static final String ANNOTATION_TYPE = "by.htp.itacademy.annotation.Immutable";
	
	private final static DevLog log = new DevLog("C:/1/logGeneratingAnnotationProcessor.txt");
				
	@Override
	public boolean process(final Set< ? extends TypeElement > annotations, 
	        final RoundEnvironment roundEnv) {
		log.logRewrite("annotations: " + annotations);
	    log.logRewrite("roundEnv: " + roundEnv);
	    
		for( final Element element: roundEnv.getElementsAnnotatedWith( Immutable.class ) ) {
			if( element instanceof TypeElement ) {
				final TypeElement typeElement = ( TypeElement )element;
				final PackageElement packageElement = ( PackageElement )typeElement.getEnclosingElement();

				log.logRewrite("typeElement: " + typeElement);
				log.logRewrite("packageElement: " + packageElement);
				try {
				    final String className = typeElement.getSimpleName() + "Immutable";
                    final JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(
                        className, typeElement);
                    
                    log.logRewrite("className: " + className);
                    log.logRewrite("fileObject: " + fileObject);
                    try( Writer writter = fileObject.openWriter() ) {
                        writter.append( "package " + packageElement.getQualifiedName() + ";" );
                        writter.append( "\n\n");
                        writter.append( "public class " + className + " {" );
                        writter.append( "\n");
                        writter.append( "}");
                        log.logRewrite("writter: " + writter);
                    }
                } catch( final IOException ex ) {
                    processingEnv.getMessager().printMessage(Kind.ERROR, ex.getMessage());
                }
			}
		}
		
		// Claiming that annotations have been processed by this processor 
		return true;
	}
}
