package by.htp.itacademy.module;

public interface Module {
	
	public static final int EXIT_SUCCESS = 0;
	public static final int EXIT_FAILURE = 1;
	
	void load();
	int run();
	void unload();
}
