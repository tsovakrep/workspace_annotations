package by.htp.itacademy.javac;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;

public class CompilingFromFile {
    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        System.out.println(compiler);
        int result = compiler.run(null, null, null,
                new File("resource/sources/Test.java").getAbsolutePath());
        if (result == 0) {
            System.out.println("compilation done");
        }
    }
}