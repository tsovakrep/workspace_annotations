package by.htp.itacademy.annotation;

import java.util.Iterator;

import javax.annotation.processing.Processor;

import com.sun.tools.javac.util.ServiceLoader;

public class Tsovak {
	
	@Time
	public void getName() {
		System.out.println("Hi, Tsovak");
	}
	
	public static Processor getInstance() {
		
		final Iterator<Processor> providers = ServiceLoader.load(Processor.class).iterator();
		if (providers.hasNext()) {
			return providers.next();
		}
		return null;
	}
	
}
