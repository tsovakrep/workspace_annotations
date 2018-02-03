package by.htp.itacademy.processor;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCStatement;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.JCTree.Tag;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import by.htp.itacademy.annotation.Time;

@SupportedAnnotationTypes(value = { CurrentTestTimeAnnotationProcessor.ANNOTATION_TYPE })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CurrentTestTimeAnnotationProcessor extends AbstractProcessor {

	public static final String ANNOTATION_TYPE = "by.htp.itacademy.annotation.Time";
	private JavacProcessingEnvironment javacProcessingEnv;
	private TreeMaker maker;

	@Override
	public void init(ProcessingEnvironment procEnv) {
		super.init(procEnv);
		this.javacProcessingEnv = (JavacProcessingEnvironment) procEnv;
		this.maker = TreeMaker.instance(javacProcessingEnv.getContext());
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (annotations == null || annotations.isEmpty()) {
			return false;
		}

		final Elements elements = javacProcessingEnv.getElementUtils();
		JavacElements utils = (JavacElements) elements;
		
		final TypeElement annotation = elements.getTypeElement(ANNOTATION_TYPE);
		if (annotation != null) {

			final Set<? extends Element> methods = roundEnv.getElementsAnnotatedWith(annotation);

			for (final Element m : methods) {

				Time time = m.getAnnotation(Time.class);

				if (time != null) {
					JCTree blockNode = utils.getTree(m);

					if (blockNode instanceof JCMethodDecl) {
						final List<JCStatement> statements = ((JCMethodDecl) blockNode).body.stats;

						List<JCStatement> newStatements = List.nil();
						
						JCExpression curTime = currentTime(utils);
						
						JCVariableDecl var_start = varTime(utils, curTime, "time_start");
						
						JCVariableDecl var_end = varTime(utils, curTime, "time_end");
						

						JCExpression elapsedTime = maker.Binary(Tag.MINUS,
								maker.Ident(var_end.name), maker.Ident(var_start.name));
						
						JCStatement log = log(utils, elapsedTime, blockNode);
						
						newStatements = newStatements.append(var_start);
						for (JCStatement oldStatement : statements) {
							newStatements = newStatements.append(oldStatement);
						}
						newStatements = newStatements.append(var_end);
						newStatements = newStatements.append(log);
						
						((JCMethodDecl) blockNode).body.stats = newStatements;
					}
				}
			}

			return true;
		}

		return false;
	}
	
	private JCExpression currentTime(JavacElements utils) {
		JCExpression exp = maker.Ident(utils.getName("System"));
		String methodName = "nanoTime";
		exp = maker.Select(exp, utils.getName(methodName));
		return maker.Apply(List.<JCExpression>nil(), exp, List.<JCExpression>nil());
	}
	
	private JCVariableDecl varTime(JavacElements utils, JCExpression currentTime, String varName) {
		JCVariableDecl var = maker.VarDef(maker.Modifiers(Flags.FINAL),
				utils.getName(varName), maker.TypeIdent(com.sun.tools.javac.code.TypeTag.LONG), currentTime);
		return var;
	}
	
	private JCStatement log(JavacElements utils, JCExpression value, JCTree blockNode) {
		JCExpression logExp = maker.Ident(utils.getName("log"));
		logExp = maker.Select(logExp, utils.getName("logRewrite"));
				
		List<JCExpression> listArgs = List.nil();
		JCExpression arg = maker.Binary(Tag.PLUS, maker.Literal(((JCMethodDecl) blockNode).getClass().toString()), maker.Literal(" --> "));
//		arg = maker.Binary(Tag.PLUS, maker.Literal(((JCMethodDecl) blockNode).name.toString()),  maker.Literal(" --> "));
//		arg = maker.Binary(Tag.PLUS, arg, maker.Literal("time: "));
//		arg = maker.Binary(Tag.PLUS, arg, value);
//		arg = maker.Binary(Tag.PLUS, arg, maker.Literal(" ns"));
		listArgs = listArgs.append(arg);
		
		JCExpression logP = maker.Apply(List.<JCExpression>nil(), logExp, listArgs);
		return maker.Exec(logP);
	}
}
