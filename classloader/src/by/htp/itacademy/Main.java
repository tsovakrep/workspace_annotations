package by.htp.itacademy;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("by.htp.itacademy.TimeAnnotationProcessor");
		Method m = ClassLoader.class.getDeclaredMethod("findLoadedClass", new Class[] { String.class });
		m.setAccessible(true);
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		Object test1 = m.invoke(cl, "by.htp.itacademy.TimeAnnotationProcessor");
		System.out.println(test1 != null);
//		ClassToTest.reportLoaded();
//		Object test2 = m.invoke(cl, "by.htp.itacademy.Tsovak");
//		System.out.println(test2 != null);
	}

//	static class ClassToTest {
//		static {
//			System.out.println("Loading " + ClassToTest.class.getName());
//		}
//
//		static void reportLoaded() {
//			System.out.println("Loaded");
//		}
//	}

}
