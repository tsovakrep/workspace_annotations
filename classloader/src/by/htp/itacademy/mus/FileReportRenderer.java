package by.htp.itacademy.mus;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileReportRenderer extends ReportRenderer {

	@Override
	public void generateReport() {
		final List<String> music = findMusic();
		try {
			final FileWriter writer = new FileWriter("music.txt");
			for (String composition : music) {
				writer.append(composition);
				writer.append("\n");
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
