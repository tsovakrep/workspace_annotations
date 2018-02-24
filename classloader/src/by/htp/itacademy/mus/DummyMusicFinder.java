package by.htp.itacademy.mus;

import java.util.Collections;
import java.util.List;

public class DummyMusicFinder implements MusicFinder {

	@Override
	public List<String> getMusic() {
		return Collections.singletonList("From DummyMusicFinder...");
	}
	
}
