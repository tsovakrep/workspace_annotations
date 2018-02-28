package annotationapi.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

public class FileFinder {

	private File path;
	private String[] extensions;

	private List<File> fileContainer;
	
	public FileFinder() {
		super();
	}

	public FileFinder(File path, String... extensions) {
		this.path = path;
		this.extensions = extensions;
		this.fileContainer = new ArrayList<>();
	}

	private void fileFind(File path, String... extensions) {
		File[] filesList = path.listFiles();

		for (File file : filesList) {
			if (file.isDirectory()) {
				File[] files = file.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						for (String extension : extensions) {
							if (name.endsWith(".".concat(extension).toLowerCase()))
								return true;
						}
						return false;
					}
				});
				fileContainer.addAll(Arrays.asList(files));

				fileFind(file, extensions);
			}
		}
	}

	public List<File> getFileContainer() {
		fileFind(path, extensions);
		return fileContainer;
	}
	
	public Set<String> searchResourceFiles(ServletContext context, String resourcePath, Set<String> temp) {
		Set<String> list = context.getResourcePaths(resourcePath);
		Set<String> linkedSet = new HashSet<>();
		linkedSet.addAll(list);
		
		for (String file : linkedSet) {
			if(file.endsWith("/")) {
				searchResourceFiles(context, file, temp);
			} else {
				temp.add(file);
			}
		}
		return temp;
	}
}
