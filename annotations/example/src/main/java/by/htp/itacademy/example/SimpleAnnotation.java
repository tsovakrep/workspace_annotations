package by.htp.itacademy.example;

import by.htp.itacademy.annotation.Complexity;
import by.htp.itacademy.utility.complexity.ComplexityLevel;

@Complexity(ComplexityLevel.VERY_SIMPLE)
public class SimpleAnnotation {

	public SimpleAnnotation() {
		super();
	}
	@Complexity()
	public void theMethod() {
		System.out.println("console output");
	}
}
