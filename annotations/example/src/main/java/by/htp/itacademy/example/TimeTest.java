package by.htp.itacademy.example;

import by.htp.itacademy.annotation.Time;
import by.htp.itacademy.annotation.Time.TimeInterval;
import by.htp.itacademy.utility.log.DevLog;

public class TimeTest {

	private final static DevLog log = new DevLog("C:/1/logTimeAnnotationProcessor.txt");
	
	@Time(interval = TimeInterval.NANOSECOND)
	public void countingTime() {
		System.out.println("Test: ");
	}
}

