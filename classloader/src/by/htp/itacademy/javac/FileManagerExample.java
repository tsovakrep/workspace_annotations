package by.htp.itacademy.javac;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This example still compiles the source from file
 */

public class FileManagerExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        //Already available DiagnosticListener implementation
        DiagnosticCollector<JavaFileObject> diagnostics =
                new DiagnosticCollector<>();

        //that's how we get the instance of StandardJavaFileManager
        //the compiler just returns the new instance.
        StandardJavaFileManager standardFileManager =
                compiler.getStandardFileManager(diagnostics, null, null);

        File file = new File("resource/FileWithErrors.java");

        //files are wrapped into JavaFileObjects by StandardFileManager
        Iterable<? extends JavaFileObject> javaFileObjects =
                standardFileManager.getJavaFileObjects(file);
        for (JavaFileObject javaFileObject : javaFileObjects) {
            System.out.println(javaFileObject.getClass());
        }


        //we still have to pass file manager and others as compiler doesn't track
        //anything about them. Also there might be a new instance of customized
        //StandardFileManager here.
        JavaCompiler.CompilationTask task = compiler.getTask(
                null, standardFileManager, diagnostics, null,
                null, javaFileObjects);

        Future<Boolean> future = Executors.newFixedThreadPool(1).submit(task);
        Boolean result = future.get();
        if (result != null && result) {
            System.out.println("Compilation done");
        } else {
            // we might show the diagnostics other than
            // standard output e.g. in some GUI screen.
            diagnostics.getDiagnostics().forEach(System.out::println);
        }
        standardFileManager.close();
    }
}