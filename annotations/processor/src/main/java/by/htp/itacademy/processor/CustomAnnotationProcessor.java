package by.htp.itacademy.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;
import javax.tools.Diagnostic;

import by.htp.itacademy.annotation.CustomAnnotation;
import by.htp.itacademy.utility.log.DevLog;

@SupportedAnnotationTypes(value = { CustomAnnotationProcessor.ANNOTATION_TYPE })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CustomAnnotationProcessor extends AbstractProcessor {

	public static final String ANNOTATION_TYPE = "by.htp.itacademy.annotation.CustomAnnotation";
	private final static DevLog log = new DevLog("C:/1/logCustomAnnotation.txt");
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		log.logRewrite("annotations: " + annotations);
		log.logRewrite("roundEnv: " + roundEnv);
		for (Element e : roundEnv.getElementsAnnotatedWith(CustomAnnotation.class)) {
			log.logRewrite("e: " + e);
			CustomAnnotation ca = e.getAnnotation(CustomAnnotation.class);
			String name = e.getSimpleName().toString();
			log.logRewrite("name: " + name);
			char[] c = name.toCharArray();
			c[0] = Character.toUpperCase(c[0]);
			name = new String(name);
			log.logRewrite("name: " + name);
			TypeElement clazz = (TypeElement) e.getEnclosingElement();
			log.logRewrite("clazz: " + clazz);
			try {
				JavaFileObject f = processingEnv.getFiler().createSourceFile(clazz.getQualifiedName() + "Autogenerate");
				log.logRewrite("f: " + f);
				processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Creating " + f.toUri());
				Writer w = f.openWriter();
				log.logRewrite("w: " + w);
				try {
					String pack = clazz.getQualifiedName().toString();
					log.logRewrite("pack: " + pack);
					PrintWriter pw = new PrintWriter(w);
					pw.println("package " + pack.substring(0, pack.lastIndexOf('.')) + ";");
					pw.println("\npublic class " + clazz.getSimpleName() + "Autogenerate {");

					TypeMirror type = e.asType();

					pw.println("\n    public " + ca.className() + " result = \"" + ca.value() + "\";");

					pw.println("    public int type = " + ca.type() + ";");

					pw.println("\n    protected " + clazz.getSimpleName() + "Autogenerate() {}");
					pw.println("\n    /** Handle something. */");
					pw.println("    protected final void handle" + name + "(" + ca.className() + " value" + ") {");
					pw.println("\n//" + e);
					pw.println("//" + ca);
					pw.println("\n        System.out.println(value);");
					pw.println("    }");
					pw.println("}");
					log.logRewrite("pw: " + pw);
					log.logRewrite("w: " + w.toString());
					pw.flush();
				} finally {
					w.close();
				}
			} catch (IOException x) {
				processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, x.toString());
			}
		}
		return true;
	}
}
