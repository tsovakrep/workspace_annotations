package by.htp.itacademy.processor;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCBlock;
import com.sun.tools.javac.tree.JCTree.JCCatch;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCExpressionStatement;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCStatement;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;

import by.htp.itacademy.annotation.Time;
import by.htp.itacademy.utility.log.DevLog;

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

@SupportedAnnotationTypes(value = { CurrentTimeAnnotationProcessor.ANNOTATION_TYPE })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CurrentTimeAnnotationProcessor extends AbstractProcessor {

	public static final String ANNOTATION_TYPE = "by.htp.itacademy.annotation.Time";
	private JavacProcessingEnvironment javacProcessingEnv;
	private TreeMaker maker;
	
	private final static DevLog log = new DevLog("C:/1/logCurrentTimeAnnotationProcessor.txt");

	@Override
	public void init(ProcessingEnvironment procEnv) {
		super.init(procEnv);
		this.javacProcessingEnv = (JavacProcessingEnvironment) procEnv;
		log.logRewrite(log.getLineNumber() + ": " + "javacProcessingEnv: " + javacProcessingEnv);
		
		this.maker = TreeMaker.instance(javacProcessingEnv.getContext());
		log.logRewrite(log.getLineNumber() + ": " + "maker: " + maker);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		log.logRewrite(log.getLineNumber() + ": " + "annotations: " + annotations);
		log.logRewrite(log.getLineNumber() + ": " + "roundEnv: " + roundEnv);
		if (annotations == null || annotations.isEmpty()) {
			return false;
		}

		final Elements elements = javacProcessingEnv.getElementUtils();
		log.logRewrite(log.getLineNumber() + ": " + "elements: " + elements);

		final TypeElement annotation = elements.getTypeElement(ANNOTATION_TYPE);
		log.logRewrite("annotation: " + annotation);
		if (annotation != null) {
			// Выбираем все элементы, у которых стоит наша аннотация
			final Set<? extends Element> methods = roundEnv.getElementsAnnotatedWith(annotation);
			log.logRewrite(log.getLineNumber() + ": " + "methods: " + methods);
			
			JavacElements utils = (JavacElements) elements;//javacProcessingEnv.getElementUtils();
			log.logRewrite(log.getLineNumber() + ": " + "utils: " + utils);
			
			for (final Element m : methods) {
				log.logRewrite(log.getLineNumber() + ": " + "m: " + m);
				
				Time time = m.getAnnotation(Time.class);
				log.logRewrite(log.getLineNumber() + ": " + "time: " + time);
				
				if (time != null) {
					JCTree blockNode = utils.getTree(m);
					log.logRewrite(log.getLineNumber() + ": " + "blockNode: " + blockNode);
					
					// Нам нужны только описания методов
					if (blockNode instanceof JCMethodDecl) {
						// Получаем содержимое метода
						final List<JCStatement> statements = ((JCMethodDecl) blockNode).body.stats;
						log.logRewrite(log.getLineNumber() + ": " + "statements: " + statements);
						
						// Новое тело метода
						List<JCStatement> newStatements = List.nil();
						log.logRewrite(log.getLineNumber() + ": " + "newStatements: " + newStatements);
						
						// Добавляем в начало метода сохранение текущего времени
						JCVariableDecl var = makeTimeStartVar(maker, utils, time);
						log.logRewrite(log.getLineNumber() + ": " + "var: " + var);
						
						newStatements = newStatements.append(var);
						log.logRewrite(log.getLineNumber() + ": " + "newStatements: " + newStatements);
						
						// Создаём тело блока try, копируем в него оригинальное содержимое метода
						List<JCStatement> tryBlock = List.nil();
						log.logRewrite(log.getLineNumber() + ": " + "tryBlock: " + tryBlock);
						
						for (JCStatement statement : statements) {
							log.logRewrite(log.getLineNumber() + ": " + "statement: " + statement);
							
							tryBlock = tryBlock.append(statement);
							log.logRewrite(log.getLineNumber() + ": " + "tryBlock: " + tryBlock);
						}

						// Создаём тело блока finally, добавляем в него вывод затраченного времени
						JCBlock finalizer = makePrintBlock(maker, utils, time, var);
						log.logRewrite(log.getLineNumber() + ": " + "finalizer: " + finalizer);
						
						JCStatement stat = maker.Try(maker.Block(0, tryBlock), List.<JCCatch>nil(), finalizer);
						log.logRewrite(log.getLineNumber() + ": " + "stat: " + stat);
						
						newStatements = newStatements.append(stat);
						log.logRewrite(log.getLineNumber() + ": " + "newStatements: " + newStatements);
						
						// Заменяем старый код метода на новый
						((JCMethodDecl) blockNode).body.stats = newStatements;
					}
				}
			}

			return true;
		}

		return false;
	}

	private JCExpression makeCurrentTime(TreeMaker maker, JavacElements utils, Time time) {
		// Создаём вызов System.nanoTime или System.currentTimeMillis
		log.logRewrite(log.getLineNumber() + ": " + "maker: " + maker);
		
		JCExpression exp = maker.Ident(utils.getName("System"));
		log.logRewrite(log.getLineNumber() + ": " + "exp: " + exp);
		
		String methodName;
		switch (time.interval()) {
		case NANOSECOND:
			methodName = "nanoTime";
			break;
		default:
			methodName = "currentTimeMillis";
			break;
		}
		exp = maker.Select(exp, utils.getName(methodName));
		log.logRewrite(log.getLineNumber() + ": " + "exp: " + exp);
		
		return maker.Apply(List.<JCExpression>nil(), exp, List.<JCExpression>nil());
	}

	protected JCVariableDecl makeTimeStartVar(TreeMaker maker, JavacElements utils, Time time) {
		// Создаём финальную переменную для хранения времени старта. Имя переменной в
		// виде time_start_{random}
		log.logRewrite(log.getLineNumber() + ": " + "maker: " + maker);
		log.logRewrite(log.getLineNumber() + ": " + "utils: " + utils);
		
		JCExpression currentTime = makeCurrentTime(maker, utils, time);
		log.logRewrite(log.getLineNumber() + ": " + "currentTime: " + currentTime);
		
		String fieldName = "time_start_" + (int) (Math.random() * 10000);
		log.logRewrite(log.getLineNumber() + ": " + "fieldName: " + fieldName);
		
		return maker.VarDef(maker.Modifiers(Flags.FINAL), utils.getName(fieldName), maker.TypeIdent(com.sun.tools.javac.code.TypeTag.LONG),
				currentTime);
	}

	protected JCBlock makePrintBlock(TreeMaker maker, JavacElements utils, Time time, JCVariableDecl var) {
		// Создаём вызов System.out.println
		log.logRewrite(log.getLineNumber() + ": " + "var: " + var);
		
		JCExpression printlnExpression = maker.Ident(utils.getName("System"));
		log.logRewrite(log.getLineNumber() + ": " + "printlnExpression: " + printlnExpression);
		
		printlnExpression = maker.Select(printlnExpression, utils.getName("out"));
		log.logRewrite(log.getLineNumber() + ": " + "printlnExpression: " + printlnExpression);
		
		printlnExpression = maker.Select(printlnExpression, utils.getName("println"));
		log.logRewrite(log.getLineNumber() + ": " + "printlnExpression: " + printlnExpression);

		// Создаём блок вычисления затраченного времени (currentTime - startTime)
		JCExpression currentTime = makeCurrentTime(maker, utils, time);
		log.logRewrite(log.getLineNumber() + ": " + "currentTime: " + currentTime);
		
		JCExpression elapsedTime = maker.Binary(com.sun.tools.javac.tree.JCTree.Tag.MINUS, currentTime, maker.Ident(var.name));
		log.logRewrite(log.getLineNumber() + ": " + "elapsedTime: " + elapsedTime);

		// Форматируем результат
		JCExpression formatExpression = maker.Ident(utils.getName("String"));
		log.logRewrite(log.getLineNumber() + ": " + "formatExpression: " + formatExpression);
		
		formatExpression = maker.Select(formatExpression, utils.getName("format"));
		log.logRewrite(log.getLineNumber() + ": " + "formatExpression: " + formatExpression);
		
		// Собираем все кусочки вместе
		List<JCExpression> formatArgs = List.nil();
		log.logRewrite(log.getLineNumber() + ": " + "formatArgs: " + formatArgs);
		
		formatArgs.append(maker.Literal(time.format()));
		log.logRewrite(log.getLineNumber() + ": " + "formatArgs: " + formatArgs);
		
		formatArgs.append(elapsedTime);
		log.logRewrite(log.getLineNumber() + ": " + "formatArgs: " + formatArgs);
		
		JCExpression format = maker.Apply(List.<JCExpression>nil(), formatExpression, formatArgs);
		log.logRewrite(log.getLineNumber() + ": " + "format: " + format);
		
		List<JCExpression> printlnArgs = List.nil();
		log.logRewrite(log.getLineNumber() + ": " + "printlnArgs: " + printlnArgs);
		
		printlnArgs.append(format);
		log.logRewrite(log.getLineNumber() + ": " + "printlnArgs: " + printlnArgs);
		
		JCExpression print = maker.Apply(List.<JCExpression>nil(), printlnExpression, printlnArgs);
		log.logRewrite(log.getLineNumber() + ": " + "print: " + print);
		
		JCExpressionStatement stmt = maker.Exec(print);
		log.logRewrite(log.getLineNumber() + ": " + "stmt: " + stmt);
		
		List<JCStatement> stmts = List.nil();
		log.logRewrite(log.getLineNumber() + ": " + "stmts: " + stmts);
		
		stmts.append(stmt);
		log.logRewrite(log.getLineNumber() + ": " + "stmts: " + stmts);
		log.logRewrite(log.getLineNumber() + ": " + "maker.Block(0, stmts): " + maker.Block(0, stmts));
		return maker.Block(0, stmts);
	}
}
