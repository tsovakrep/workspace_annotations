package by.htp.itacademy.framework.classcore;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import by.htp.itacademy.framework.util.ClassUtil;
import by.htp.itacademy.framework.util.FrameworkConstant;
import by.htp.itacademy.framework.util.ObjectUtils;

public abstract class ClassTemplate {

	protected final String packageName;

	public ClassTemplate(String packageName) {
		super();
		this.packageName = packageName;
	}

	public List<Class<?>> getClassList() {
		List<Class<?>> classList = new ArrayList<>();
		try {
			Enumeration<URL> enumerationUrl = ClassUtil.getClassLoader().getResources(packageName.replace(FrameworkConstant.DOT, FrameworkConstant.SLASH));
			System.out.println("enumerationUrl: " + enumerationUrl.hasMoreElements());
			while (enumerationUrl.hasMoreElements()) {
				URL url = enumerationUrl.nextElement();
				System.out.println("url: " + url);
				if (url != null) {
					if (isFile(url)) {
						String packagePath = URLDecoder.decode(url.getPath(), FrameworkConstant.ENCODING);
						addClass(classList, packagePath, packagePath);
					} else {
						addFileFromJar(url, classList);
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return classList;
	}

	private boolean isFile(URL url) {
		return FrameworkConstant.FILE.equalsIgnoreCase(url.getProtocol());
	}

	private void addClass(List<Class<?>> classList, String packageName, String packagePath) {
		File[] files = getFiles();
		checkAddFile(files, classList, packageName, packagePath);
	}

	private void addFileFromJar(URL url, List<Class<?>> classList) {
		try {
			JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
			JarFile jarFile = jarURLConnection.getJarFile();
			Enumeration<JarEntry> jarEntries = jarFile.entries();
			while (jarEntries.hasMoreElements()) {
				JarEntry jarEntry = jarEntries.nextElement();
				String jarEntryName = jarEntry.getName();
				if (jarEntryName.endsWith(FrameworkConstant.PREFIX)) {
					String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(FrameworkConstant.DOT)).replaceAll(FrameworkConstant.SLASH, FrameworkConstant.DOT);
					doAddClass(classList, className);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private File[] getFiles() {
		File[] files = new File(packageName).listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(FrameworkConstant.PREFIX)) || file.isDirectory();
			}
		});

		return files;
	}

	private void checkAddFile(File[] files, List<Class<?>> classList, String packageName, String packagePath) {
		for (File file : files) {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0, fileName.lastIndexOf(FrameworkConstant.DOT));
				if (ObjectUtils.isNotEmptyString(packageName)) {
					className = packageName + FrameworkConstant.DOT + className;
				}
				doAddClass(classList, className);
			} else {
				String subPackagePath = fileName;
				if (ObjectUtils.isNotEmptyString(packagePath)) {
					subPackagePath = packagePath + File.separator + subPackagePath;
				}

				String subPackageName = fileName;
				if (ObjectUtils.isNotEmptyString(packageName)) {
					subPackageName = packageName + FrameworkConstant.DOT + subPackageName;
				}
				addClass(classList, subPackageName, subPackagePath);
			}
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
