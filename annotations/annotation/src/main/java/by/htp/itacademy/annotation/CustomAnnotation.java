package by.htp.itacademy.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(SOURCE)
@Target(METHOD)
public @interface CustomAnnotation {
	
	String className();
    String value() default "Hello";
    int type() default 0;
	
}
