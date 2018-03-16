package framework.classcore.template;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

public abstract class Template {
	
	protected final String packageName;

	public Template(String packageName) {
		super();
		this.packageName = packageName;
	}
	
	protected void searchResourceFiles(ServletContext context, String resourcePath, List<?> fileList) {
		Set<String> contextList = context.getResourcePaths(resourcePath);
		Set<String> linkedSet = new HashSet<>();
		linkedSet.addAll(contextList);
		
		for (String fileName : linkedSet) {
			if(fileName.endsWith("/")) {
				searchResourceFiles(context, fileName, fileList);
			} else {
				doAddFile(fileList, fileName);
			}
		}
	}
	
	protected abstract <T> List<T> getFileList();
	
	protected abstract <T> void doAddFile(List<T> fileList, String fileName);
	
}
