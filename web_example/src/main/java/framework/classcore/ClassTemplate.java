package framework.classcore;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import framework.util.ClassUtil;

public abstract class ClassTemplate {

	protected final String packageName;

	public ClassTemplate(String packageName) {
		super();
		this.packageName = packageName;
	}

	public List<Class<?>> getClassList() {
		List<Class<?>> classList = new ArrayList<>();
		File path = new File(packageName);
		
		fileFinder(path, classList);
		
		return classList;
	}

	private void fileFinder(File path, List<Class<?>> classList) {
		File[] filesList = path.listFiles();

		for (File file : filesList) {
			if (file.isDirectory()) {
				File[] files = file.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						if (name.endsWith(".class"))
							return true;
						return false;
					}
				});
				addClass(files, classList);

				fileFinder(file, classList);
			}
		}
	}
	
	private void addClass(File[] files, List<Class<?>> classList) {
		for (File file : files) {
			if (file.getName().equals("by.htp.itacademy.framework.webcore.ContainerListener"))
				return;
			doAddClass(classList, file.getName());
		}
	}

	private void doAddClass(List<Class<?>> classList, String className) {
		Class<?> clzz = ClassUtil.loadClass(className, false);
		if (checkAddClass(clzz)) {
			classList.add(clzz);
		}
	}

	protected abstract boolean checkAddClass(Class<?> clz);
}
