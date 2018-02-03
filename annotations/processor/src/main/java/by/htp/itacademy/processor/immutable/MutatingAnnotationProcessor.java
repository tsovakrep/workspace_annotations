package by.htp.itacademy.processor.immutable;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree.JCCompilationUnit;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeTranslator;

import by.htp.itacademy.annotation.Immutable;
import by.htp.itacademy.utility.log.DevLog;

@SupportedAnnotationTypes( MutatingAnnotationProcessor.ANNOTATION_TYPE )
@SupportedSourceVersion( SourceVersion.RELEASE_8 )
public class MutatingAnnotationProcessor extends AbstractProcessor {
    
	public static final String ANNOTATION_TYPE = "by.htp.itacademy.annotation.Immutable";
	
	private final static DevLog log = new DevLog("C:/1/logMutatingAnnotationProcessor.txt");
	private Trees trees; 
    
    @Override
    public void init (ProcessingEnvironment processingEnv) {
        super.init( processingEnv );
        trees = Trees.instance( processingEnv );        
    }
    
	@Override
	public boolean process( final Set< ? extends TypeElement > annotations, 
	        final RoundEnvironment roundEnv) {
	    log.logRewrite("annotations: " + annotations);
	    log.logRewrite("roundEnv: " + roundEnv);
	    final TreePathScanner< Object, CompilationUnitTree > scanner = 
	        new TreePathScanner< Object, CompilationUnitTree >() {
    	        @Override
    	        public Trees visitClass(final ClassTree classTree, final CompilationUnitTree unitTree) {
    	        	log.logRewrite("classTree: " + classTree);
    	        	log.logRewrite("classTree: " + classTree);
                    if (unitTree instanceof JCCompilationUnit) {
                        final JCCompilationUnit compilationUnit = ( JCCompilationUnit )unitTree;
                        log.logRewrite("compilationUnit: " + compilationUnit);
                        // Only process on files which have been compiled from source
                        if (compilationUnit.sourcefile.getKind() == JavaFileObject.Kind.SOURCE) {
                            compilationUnit.accept(new TreeTranslator() {
                                public void visitVarDef( final JCVariableDecl tree ) {
                                	log.logRewrite("tree: " + tree);
                                    super.visitVarDef( tree );
                                    
                                    if ( ( tree.mods.flags & Flags.FINAL ) == 0 ) {
                                        tree.mods.flags |= Flags.FINAL;
                                    }
                                }
                            });
                        }
                    }
    
                    return trees;
    	        }
    	    };
	    
		for( final Element element: roundEnv.getElementsAnnotatedWith( Immutable.class ) ) {		   
		    final TreePath path = trees.getPath( element );
		    log.logRewrite("path: " + path);
            scanner.scan( path, path.getCompilationUnit() );
		}
		
		// Claiming that annotations have been processed by this processor 
		return true;
	}
}
