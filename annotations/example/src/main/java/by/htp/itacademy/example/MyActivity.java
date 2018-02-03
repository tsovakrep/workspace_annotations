package by.htp.itacademy.example;

import by.htp.itacademy.annotation.CustomAnnotation;
import by.htp.itacademy.annotation.Time;

public class MyActivity {

	@CustomAnnotation(className = "String", type = 1)
	public void annotatedMethod(String value) {
	}

	public void start() {
		
	}
}
