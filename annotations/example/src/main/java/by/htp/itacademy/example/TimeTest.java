package by.htp.itacademy.example;

import by.htp.itacademy.annotation.Time;
import by.htp.itacademy.utility.log.DevLog;

public class TimeTest {

	@SuppressWarnings("unused")
	private final static DevLog log = new DevLog("C:/1/logTimeAnnotationProcessor.txt");
	
	@Time
	public void countingTime() {
		System.out.println("Hi, Tsovak ");
	}
}

