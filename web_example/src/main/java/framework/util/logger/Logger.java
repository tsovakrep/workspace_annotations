package framework.util.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

	private String logFile;
	private File file;
	private boolean mark;

	public Logger(String logFile) {
		super();
		this.logFile = logFile;
	}

	public void log(Object message) {
		if (message == null) {
			return;
		}

		new File(logFile).getParentFile().mkdirs();

		FileWriter writer = null;
		try {
			writer = new FileWriter(logFile, true);
			writer.append(message.toString());
			writer.append("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				writer.close();
			} catch (IOException e1) {
			}
		}
	}

	public void logRewrite(Object message) {
		if (message == null) {
			return;
		}

		new File(logFile).getParentFile().mkdirs();

		FileWriter writer = null;
		try {
			writer = new FileWriter(newFile(), true);
			writer.append(message.toString());
			writer.append("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				writer.close();
			} catch (IOException e1) {
			}
		}
	}

	private File newFile() {

		file = new File(logFile);
		if (!mark) {
			file.delete();
			mark = true;
		}
		return file;
	}

	public int getLineNumber() {
		return currentLine();
	}

	private int currentLine() {
		boolean thisOne = false;
		int thisOneCountDown = 1;
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		for (StackTraceElement element : elements) {
			String methodName = element.getMethodName();
			int lineNum = element.getLineNumber();
			if (thisOne && (thisOneCountDown == 0)) {
				return lineNum;
			} else if (thisOne) {
				thisOneCountDown--;
			}
			if ("currentLine".equals(methodName)) {
				thisOne = true;
			}
		}
		return -1;
	}
	
	@SuppressWarnings("unused")
	private String currentClass() {
		return this.getClass().getName();
	}
}
