package by.htp.itacademy.example;

import by.htp.itacademy.annotation.Complexity;
import by.htp.itacademy.annotation.Time;
import by.htp.itacademy.utility.complexity.ComplexityLevel;
import by.htp.itacademy.utility.log.DevLog;

@Complexity(ComplexityLevel.VERY_SIMPLE)
public class SimpleAnnotation {

	private final static DevLog log = new DevLog("C:/1/logTimeAnnotationProcessor1.txt");
	
	public SimpleAnnotation() {
		super();
	}
	@Complexity()
	@Time
	public void theMethod() {
		System.out.println("console output");
	}
}
