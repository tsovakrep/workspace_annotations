package framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtil {
	private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public static Class<?> loadClass(String className) {
		return loadClass(className, true, getClassLoader());
	}
	
	public static Class<?> loadClass(String className, boolean isInit) {
		return loadClass(className, isInit, getClassLoader());
	}

	public static Class<?> loadClass(String className, boolean isInit, ClassLoader classLoader) {
		Class<?> cls = null;
		try {
			cls = Class.forName(className, true, classLoader);
		} catch (ClassNotFoundException e) {
			logger.error("class not found Exception", e);
			throw new RuntimeException(e);
		}
		return cls;
	}
//
//	public static void main(String[] args) throws ClassNotFoundException {
//		Class<?> loadClass = ClassUtil.loadClass("framework.HelperLoader");
//		System.out.println(loadClass.getName());
//		Class<?> clzz = ClassUtil.class.getClassLoader().loadClass("framework.HelperLoader");
//		System.out.println(clzz.getName());
//	}
}
