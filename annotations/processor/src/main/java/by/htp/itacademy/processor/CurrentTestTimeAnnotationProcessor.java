package by.htp.itacademy.processor;

import com.sun.source.tree.MethodTree;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symbol.VarSymbol;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCBlock;
import com.sun.tools.javac.tree.JCTree.JCCatch;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCExpressionStatement;
import com.sun.tools.javac.tree.JCTree.JCFieldAccess;
import com.sun.tools.javac.tree.JCTree.JCLiteral;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCMethodInvocation;
import com.sun.tools.javac.tree.JCTree.JCModifiers;
import com.sun.tools.javac.tree.JCTree.JCStatement;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javadoc.JavaScriptScanner;

import by.htp.itacademy.annotation.Time;
import by.htp.itacademy.utility.log.DevLog;
import sun.net.NetworkServer;

import java.util.Collections;
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

@SupportedAnnotationTypes(value = { CurrentTestTimeAnnotationProcessor.ANNOTATION_TYPE })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CurrentTestTimeAnnotationProcessor extends AbstractProcessor {

	public static final String ANNOTATION_TYPE = "by.htp.itacademy.annotation.Time";
	private JavacProcessingEnvironment javacProcessingEnv;
	private TreeMaker maker;

	private final static DevLog log = new DevLog("C:/1/logTimeAnnotationProcessor.txt");

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

		final TypeElement annotation = elements.getTypeElement(ANNOTATION_TYPE);
		if (annotation != null) {
			// Выбираем все элементы, у которых стоит наша аннотация
			final Set<? extends Element> methods = roundEnv.getElementsAnnotatedWith(annotation);

			JavacElements utils = (JavacElements) elements;// javacProcessingEnv.getElementUtils();
			
			for (final Element m : methods) {

				Time time = m.getAnnotation(Time.class);

				if (time != null) {
					JCTree blockNode = utils.getTree(m);

					// Нам нужны только описания методов
					if (blockNode instanceof JCMethodDecl) {
						// Получаем содержимое метода
						final List<JCStatement> statements = ((JCMethodDecl) blockNode).body.stats;

						// Новое тело метода
						List<JCStatement> newStatements = List.nil();

						JCExpression exp = maker.Ident(utils.getName("System"));
						String methodName = "nanoTime";
						exp = maker.Select(exp, utils.getName(methodName));
						JCExpression currentTime = maker.Apply(List.<JCExpression>nil(), exp, List.<JCExpression>nil());

						String fieldName_start = "time_start";
						JCVariableDecl var_start = maker.VarDef(maker.Modifiers(Flags.FINAL),
								utils.getName(fieldName_start), maker.TypeIdent(com.sun.tools.javac.code.TypeTag.LONG),
								currentTime);

						String fieldName_end = "time_end";
						JCVariableDecl var_end = maker.VarDef(maker.Modifiers(Flags.FINAL),
								utils.getName(fieldName_end), maker.TypeIdent(com.sun.tools.javac.code.TypeTag.LONG),
								currentTime);

						// JCExpression sysOut = maker.Ident(utils.getName("System"));
						// sysOut = maker.Select(sysOut, utils.getName("out"));
						// sysOut = maker.Select(sysOut, utils.getName("println"));
						// JCExpression concSysOut = maker.Apply(List.<JCExpression>nil(), sysOut, arg2)

						JCExpression printlnExpression = maker.Ident(utils.getName("System"));
						printlnExpression = maker.Select(printlnExpression, utils.getName("out"));
						printlnExpression = maker.Select(printlnExpression, utils.getName("println"));

						JCExpression elapsedTime = maker.Binary(com.sun.tools.javac.tree.JCTree.Tag.MINUS,
								maker.Ident(var_end.name), maker.Ident(var_start.name));
						log.logRewrite(elapsedTime);

						List<JCExpression> printlnArgs = List.nil();
						printlnArgs = printlnArgs.append(elapsedTime);
						log.logRewrite("printlnArgs: " + printlnArgs);

						JCExpression print = maker.Apply(List.<JCExpression>nil(), printlnExpression, printlnArgs);

						JCExpressionStatement stmt = maker.Exec(print);
						
						
						
						
						JCExpression log1 = maker.Ident(utils.getName("log"));
						log1 = maker.Select(log1, utils.getName("logRewrite"));

						List<JCExpression> logL = List.nil();
						logL = logL.append(log1);

						JCExpression logP = maker.Apply(List.<JCExpression>nil(), log1, printlnArgs);

						JCExpressionStatement stmt1 = maker.Exec(logP);
						
						

						newStatements = newStatements.append(var_start);
						for (JCStatement oldStatement : statements) {
							newStatements = newStatements.append(oldStatement);
						}
						newStatements = newStatements.append(var_end);
						newStatements = newStatements.append(stmt);
						newStatements = newStatements.append(stmt1);
						log.logRewrite(newStatements);
						((JCMethodDecl) blockNode).body.stats = newStatements;
					}
				}
			}

			return true;
		}

		return false;
	}
}
