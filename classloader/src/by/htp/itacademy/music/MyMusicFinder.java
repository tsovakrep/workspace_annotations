package by.htp.itacademy.music;

import java.util.Collections;
import java.util.List;

/**
 * @author Ivan Khalopik
 * @since 1.0
 */
public class MyMusicFinder implements MusicFinder {
	public List<String> getMusic() {
		return Collections.singletonList("From MyMusicFinder...");
	}
}
