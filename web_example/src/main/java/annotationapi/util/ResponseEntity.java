package annotationapi.util;

public class ResponseEntity<T> {
	
	private T body;
	private String page;
	private HttpStatus status;

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

	public T getBody() {
		return body;
	}

	public String getPage() {
		return page;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
}
