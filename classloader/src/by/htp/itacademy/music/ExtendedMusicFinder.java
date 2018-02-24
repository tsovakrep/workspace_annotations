
package by.htp.itacademy.music;

import java.util.Collections;
import java.util.List;

public class ExtendedMusicFinder implements MusicFinder {
	public List<String> getMusic() {
		return Collections.singletonList("From ExtendedMusicFinder...");
	}
}
