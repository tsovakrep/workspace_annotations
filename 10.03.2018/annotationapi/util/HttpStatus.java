package annotationapi.util;

public enum HttpStatus {
	OK(200, "OK");
	
	private final int value;
	private final String phrase;
	
	private HttpStatus(int value, String phrase) {
		this.value = value;
		this.phrase = phrase;
	}
	
	public int value() {
		return this.value;
	}
	
	public String getPhrase() {
		return this.phrase;
	}
}
