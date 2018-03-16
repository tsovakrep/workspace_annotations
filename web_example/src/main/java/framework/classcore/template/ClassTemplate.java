package framework.classcore.template;

import java.util.ArrayList;
import java.util.List;

import framework.util.ClassUtil;
import framework.util.FrameworkConstant;
import framework.webcore.DataContext;

public abstract class ClassTemplate extends Template {

	public ClassTemplate(String packageName) {
		super(packageName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getFileList() {
		List<Class<?>> classList = new ArrayList<>();
		searchResourceFiles(DataContext.getServletContext(), FrameworkConstant.CLASSES_PACKAGE, classList);
		return (List<T>) classList;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T> void doAddFile(List<T> fileList, String fileName) {
		fileName = fileName.replaceAll(FrameworkConstant.CLASSES_PACKAGE, "by.").replaceAll(".class", "").replaceAll("/", ".");
		Class<?> clzz = ClassUtil.loadClass(fileName);
		if (checkAddClass(clzz)) {
			fileList.add((T) clzz);
		}
	}

	protected abstract boolean checkAddClass(Class<?> clz);
}
