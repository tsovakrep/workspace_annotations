package by.htp.itacademy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ModuleLoader extends ClassLoader {

	private String pathtobin;

	public ModuleLoader(String pathtobin, ClassLoader parent) {
		super(parent);
		this.pathtobin = pathtobin;
	}

	@Override
	public Class<?> findClass(String className) throws ClassNotFoundException {
		try {
			/**
			 * ������� ����-��� �� ����� � ��������� ����� � �������
			 */
			byte b[] = fetchClassFromFS(pathtobin + className + ".class");
			return defineClass(className, b, 0, b.length);
		} catch (FileNotFoundException ex) {
			return super.findClass(className);
		} catch (IOException ex) {
			return super.findClass(className);
		}

	}

	private byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream(new File(path));

		// Get the size of the file
		long length = new File(path).length();

		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + path);
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;

	}

}
