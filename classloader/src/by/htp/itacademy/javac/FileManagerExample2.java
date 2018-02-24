package by.htp.itacademy.javac;

import javax.tools.*;
import java.io.File;
import java.io.IOException;

/**
 * This example still compiles the source from file
 */

public class FileManagerExample2 {

    public static void main(String[] args) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        System.out.println(compiler);
        MyDiagnosticListener listener = new MyDiagnosticListener();
        StandardJavaFileManager fileManager =
                compiler.getStandardFileManager(listener, null, null);

        File file = new File("resource/FileWithErrors.java");
        Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task =
                compiler.getTask(null, fileManager, listener, null, null, javaFileObjects);
        if (task.call()) {
            System.out.println("compilation done");
        }
        fileManager.close();
    }

    private static final class MyDiagnosticListener implements DiagnosticListener {
        @Override
        public void report(Diagnostic diagnostic) {
            //to have more control over formatting etc, use indivual methods of
            //diagnostic instead
            System.out.println(diagnostic);
        }
    }
}