package annotationapi.util;

import lombok.Getter;

public class ResponseEntity<T> {
	
	@Getter private T body;
	@Getter private String page;
	@Getter private HttpStatus status;

	public ResponseEntity() {
		
	}

	public ResponseEntity(String page, HttpStatus status) {
		this(status);
		this.page = page;
	}

	public ResponseEntity(HttpStatus status) {
		this.status = status;
	}

	public ResponseEntity(T body, HttpStatus status) {
		this(status);
		this.body = body;
	}

	public ResponseEntity(T body, String page, HttpStatus status) {
		this(body, status);
		this.page = page;
	}
}
