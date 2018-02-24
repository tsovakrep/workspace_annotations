package by.htp.itacademy.mus;

import java.util.Collections;
import java.util.List;

public class MyMusicFinder implements MusicFinder {
	public List<String> getMusic() {
		return Collections.singletonList("From MyMusicFinder...");
	}
}