package by.htp.itacademy.music;

import java.util.*;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class ReportRenderer {
	public void generateReport() {
		final List<String> music = findMusic();
		for (String composition : music) {
			System.out.println(composition);
		}
	}

	public List<String> findMusic() {
		final List<String> music = new ArrayList<String>();
		for (final MusicFinder finder : SPIUtils.resolveAll(MusicFinder.class)) {
			music.addAll(finder.getMusic());
		}
		Collections.sort(music);
		return music;
	}

	public static ReportRenderer getInstance() {
		final ReportRenderer renderer = SPIUtils.resolve(ReportRenderer.class);
		return renderer != null ? renderer : new ReportRenderer();
	}

	public static void main(String[] args) {
		final ReportRenderer renderer = getInstance();
		renderer.generateReport();
	}
}
