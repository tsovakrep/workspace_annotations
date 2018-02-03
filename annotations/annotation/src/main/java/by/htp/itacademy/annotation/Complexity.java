package by.htp.itacademy.annotation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import by.htp.itacademy.utility.complexity.ComplexityLevel;

@Documented
@Retention(SOURCE)
@Target({ TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR })
public @interface Complexity {
	ComplexityLevel value() default ComplexityLevel.MEDIUM;
}
