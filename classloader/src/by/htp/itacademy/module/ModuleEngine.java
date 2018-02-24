package by.htp.itacademy.module;

import java.io.File;

public class ModuleEngine {

	public static void main(String args[]) {
		String modulePath = "E:\\eclipse\\workspace_annotations\\nhn\\bin\\by\\htp";

		ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());


		File dir = new File(modulePath);
		String[] modules = dir.list();


		for (String module : modules) {
			try {
				String moduleName = module.split(".class")[0];
				Class<?> clazz = loader.loadClass(moduleName);
				Module execute = (Module) clazz.newInstance();

				execute.load();
				execute.run();
				execute.unload();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

}
