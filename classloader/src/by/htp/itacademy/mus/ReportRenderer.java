package by.htp.itacademy.mus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class ReportRenderer {

	public void generateReport() {
		final List<String> music = findMusic();
		for (String composition : music) {
			System.out.println(composition);
		}
	}

	public List<String> findMusic() {
		final List<String> music = new ArrayList<String>();
		for (final MusicFinder finder : ServiceLoader.load(MusicFinder.class)) {
			music.addAll(finder.getMusic());
		}
		Collections.sort(music);
		return music;
	}

	public static ReportRenderer getInstance() {
		final Iterator<ReportRenderer> providers = ServiceLoader.load(ReportRenderer.class).iterator();

		if (providers.hasNext()) {
			return providers.next();
		}

		return new ReportRenderer();
	}

	public static void main(final String[] args) {
		final ReportRenderer renderer = ReportRenderer.getInstance();
		renderer.generateReport();
	}
}
