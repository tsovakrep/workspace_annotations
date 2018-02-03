package by.htp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class MainLoader extends ClassLoader {

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println("before: " + name);
		if (!"by.htp.Tsovak".equals(name)) {
			return super.loadClass(name);
		}
		System.out.println("after: " + name);
		
		try {
			String url = "E:/eclipse/workspace_annotations/exam/bin/by/htp/Tsovak.class";
			File file = new File(url);
			InputStream input = new FileInputStream(file);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int data = input.read();

			while (data != -1) {
				buffer.write(data);
				data = input.read();
			}

			input.close();

			byte[] classData = buffer.toByteArray();

			return defineClass(name, classData, 0, classData.length);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
