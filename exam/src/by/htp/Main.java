package by.htp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
//		File mai = new File(Main.class.getName());
//		String absolutePath = mai.getAbsolutePath();
//		int lastIn = absolutePath.lastIndexOf('\\');
//		String path = absolutePath.substring(0, lastIn);
//		File startDir = new File("E:\\eclipse\\workspace\\victor\\hotel");
//
//		for (File file : new FileFinder(startDir, "class").getFileContainer()) {
//			System.out.println("file path: " + file.getAbsolutePath());
//		}
		
//		for (;;) {
//			ClassLoader cl = new MainLoader();
//			Class<?> clazz = Class.forName("by.htp.Tsovak", true, cl);
//			Object obj = clazz.newInstance();
//			System.out.println(obj);
//			new BufferedReader(new InputStreamReader(System.in)).readLine();
//		}
		Tsovak t = (Tsovak) Thread.currentThread().getContextClassLoader().loadClass("by.htp.Tsovak").newInstance();
		t.main(args);
	}
}