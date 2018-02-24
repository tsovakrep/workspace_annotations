package by.htp.itacademy.annotation;

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

@SupportedAnnotationTypes(value = { TimeAnnotationProcessor.ANNOTATION_TYPE })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TimeAnnotationProcessor extends AbstractProcessor {

	public static final String ANNOTATION_TYPE = "by.htp.itacademy.Time";
	private JavacProcessingEnvironment javacProcessingEnv;
	private TreeMaker maker;

	private final static DevLog log = new DevLog("C:/logTimeAnnotationProcessor.txt");

	@Override
	public void init(ProcessingEnvironment procEnv) {
		super.init(procEnv);
		this.javacProcessingEnv = (JavacProcessingEnvironment) procEnv;
		log.logRewrite("javacProcessingEnv: " + javacProcessingEnv);
		this.maker = TreeMaker.instance(javacProcessingEnv.getContext());
		log.logRewrite("maker: " + maker);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		log.logRewrite("annotations: " + annotations);
		log.logRewrite("roundEnv: " + roundEnv);
		if (annotations == null || annotations.isEmpty()) {
			return false;
		}

		final Elements elements = javacProcessingEnv.getElementUtils();
		log.logRewrite("elements: " + elements);

		final TypeElement annotation = elements.getTypeElement(ANNOTATION_TYPE);
		log.logRewrite("annotation: " + annotation);
		if (annotation != null) {
			// Выбираем все элементы, у которых стоит
			// наша аннотация
			final Set<? extends Element> methods = roundEnv.getElementsAnnotatedWith(annotation);
			log.logRewrite("methods: " + methods);
			JavacElements utils = javacProcessingEnv.getElementUtils();
			log.logRewrite("utils: " + utils);
			for (final Element m : methods) {
				log.logRewrite("m: " + m);
				Time time = m.getAnnotation(Time.class);
				log.logRewrite("time: " + time);
				if (time != null) {
					JCTree blockNode = utils.getTree(m);
					log.logRewrite("blockNode: " + blockNode);
					// Нам нужны только описания методов
					if (blockNode instanceof JCMethodDecl) {
						// Получаем содержимое метода
						final List<JCStatement> statements = ((JCMethodDecl) blockNode).body.stats;
						log.logRewrite("statements: " + statements);
						// Новое тело метода
						List<JCStatement> newStatements = List.nil();
						log.logRewrite("newStatements: " + newStatements);
						// Добавляем в начало метода сохранение
						// текущего времени
						JCVariableDecl var = makeTimeStartVar(maker, utils, time);
						log.logRewrite("var: " + var);
						newStatements = newStatements.append(var);
						log.logRewrite("newStatements: " + newStatements);
						// Создаём тело блока try, копируем в него
						// оригинальное содержимое метода
						List<JCStatement> tryBlock = List.nil();
						log.logRewrite("tryBlock: " + tryBlock);
						for (JCStatement statement : statements) {
							log.logRewrite("statement: " + statement);
							tryBlock = tryBlock.append(statement);
							log.logRewrite("tryBlock: " + tryBlock);
						}

						// Создаём тело блока finally, добавляем в него
						// вывод затраченного времени
						JCBlock finalizer = makePrintBlock(maker, utils, time, var);
						log.logRewrite("finalizer: " + finalizer);
						JCStatement stat = maker.Try(maker.Block(0, tryBlock), List.<JCCatch>nil(), finalizer);
						log.logRewrite("stat: " + stat);
						newStatements = newStatements.append(stat);
						log.logRewrite("newStatements: " + newStatements);
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
		log.logRewrite("maker: " + maker);
		JCExpression exp = maker.Ident(utils.getName("System"));
		log.logRewrite("exp: " + exp);
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
		log.logRewrite("exp: " + exp);

		return maker.Apply(List.<JCExpression>nil(), exp, List.<JCExpression>nil());
	}

	protected JCVariableDecl makeTimeStartVar(TreeMaker maker, JavacElements utils, Time time) {
		// Создаём финальную переменную для
		// хранения времени старта. �?мя переменной в
		// виде time_start_{random}
		log.logRewrite("maker: " + maker);
		log.logRewrite("utils: " + utils);
		JCExpression currentTime = makeCurrentTime(maker, utils, time);
		log.logRewrite("currentTime: " + currentTime);
		String fieldName = "time_start_" + (int) (Math.random() * 10000);
		log.logRewrite("fieldName: " + fieldName);
		return maker.VarDef(maker.Modifiers(Flags.FINAL), utils.getName(fieldName),
				maker.TypeIdent(com.sun.tools.javac.code.TypeTag.LONG), currentTime);
	}

	protected JCBlock makePrintBlock(TreeMaker maker, JavacElements utils, Time time, JCVariableDecl var) {
		// Создаём вызов System.out.println
		// log.logRewrite("var: " + var);
		JCExpression printlnExpression = maker.Ident(utils.getName("System"));
		// log.logRewrite("printlnExpression: " + printlnExpression);
		printlnExpression = maker.Select(printlnExpression, utils.getName("out"));
		// log.logRewrite("printlnExpression: " + printlnExpression);
		printlnExpression = maker.Select(printlnExpression, utils.getName("println"));
		// log.logRewrite("printlnExpression: " + printlnExpression);

		// Создаём блок вычисления затраченного
		// времени (currentTime - startTime)
		JCExpression currentTime = makeCurrentTime(maker, utils, time);
		log.logRewrite("currentTime: " + currentTime);
		JCExpression elapsedTime = maker.Binary(com.sun.tools.javac.tree.JCTree.Tag.MINUS, currentTime,
				maker.Ident(var.name));
		log.logRewrite("elapsedTime: " + elapsedTime);

		// Форматируем результат
		JCExpression formatExpression = maker.Ident(utils.getName("String"));
		log.logRewrite("formatExpression: " + formatExpression);
		formatExpression = maker.Select(formatExpression, utils.getName("format"));
		log.logRewrite("formatExpression: " + formatExpression);
		// Собираем все кусочки вместе
		List<JCExpression> formatArgs = List.nil();
		log.logRewrite("formatArgs: " + formatArgs);
		formatArgs = formatArgs.append(maker.Literal(time.format()));
		log.logRewrite("formatArgs: " + formatArgs);
		formatArgs = formatArgs.append(elapsedTime);
		log.logRewrite("formatArgs: " + formatArgs);
		JCExpression format = maker.Apply(List.<JCExpression>nil(), formatExpression, formatArgs);
		log.logRewrite("format: " + format);
		List<JCExpression> printlnArgs = List.nil();
		log.logRewrite("printlnArgs: " + printlnArgs);
		printlnArgs = printlnArgs.append(format);
		log.logRewrite("printlnArgs: " + printlnArgs);
		JCExpression print = maker.Apply(List.<JCExpression>nil(), printlnExpression, printlnArgs);
		log.logRewrite("print: " + print);
		JCExpressionStatement stmt = maker.Exec(print);
		log.logRewrite("stmt: " + stmt);
		List<JCStatement> stmts = List.nil();
		log.logRewrite("stmts: " + stmts);
		stmts = stmts.append(stmt);
		log.logRewrite("stmts: " + stmts);
		return maker.Block(0, stmts);
	}
}
