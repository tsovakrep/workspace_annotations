package framework.core.impl.support;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import framework.FrameworkConstant;
import framework.util.ClassUtil;

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

public abstract class ClassTemplate {
    private Logger logger = LoggerFactory.getLogger(ClassTemplate.class);

    protected final String packageName;

    public ClassTemplate(String packageName) {
        this.packageName = packageName;
    }

    public List<Class<?>> getClassList() {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        try {
            Enumeration<URL> enumerationUrl = ClassUtil.getClassLoader().getResources(packageName.replace(".", "/"));
            while (enumerationUrl.hasMoreElements()) {
                URL url = enumerationUrl.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if ("file".equalsIgnoreCase(protocol)) {
//                        String packagePath = url.getPath().replaceAll("%20", " ");
                        String packagePath = URLDecoder.decode(url.getPath(), FrameworkConstant.ENCODING);
                        addClass(classList, packageName, packagePath);
                    } else {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
                            if (jarEntryName.endsWith(".class")) {
                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                doAddClass(classList, className);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("������������������������������������", e);
            throw new RuntimeException(e);
        }
        return classList;
    }

    private void addClass(List<Class<?>> classList, String packageName, String packagePath) {

        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtils.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classList, className);
            } else {
                String subPackagePath = fileName;
                if (StringUtils.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + File.separator + subPackagePath;
                }

                String subPackageName = fileName;
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
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

    protected abstract boolean checkAddClass(Class<?> clz) ;

    public static void main(String[] args) {
        URL url = ClassUtil.getClassLoader().getResource("org/smart4j/framework");
        String urlPath = url.getPath();
        System.out.println(urlPath);
//        File[] files = new File(urlPath).listFiles();
//        for (File f : files) {
//            System.out.println(f.getName());
//        }
    }
}

