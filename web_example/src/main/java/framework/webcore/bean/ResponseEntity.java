package framework.webcore.bean;

import framework.webcore.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ResponseEntity<T> {

	@Getter
	private T body;
	@Getter
	private String path;
	@Getter
	private HttpStatus status;

	public ResponseEntity() {}
	
	public ResponseEntity(T body) {
		this(body, null, null);
	}
	
	public ResponseEntity(T body, String path) {
		this(body, path, null);
	}
	
	public ResponseEntity(String path, HttpStatus status) {
		this(null, path, status);
	}
	
	public ResponseEntity(T body, HttpStatus status) {
		this(body, null, status);
	}
	
	public ResponseEntity(HttpStatus status) {
		this(null, null, null);
	}
	
	public boolean isNotRedirect() {
        return this.path.startsWith("/");
    }
}