package framework.webcore.http;

import java.util.HashMap;
import java.util.Map;

public enum HttpStatus {
	// 1xx Informational
	
	CONTINUE(100, "Continue"),
	SWITCHING_PROTOCOLS(101, "Switching Protocols"),
	PROCESSING(102, "Processing"),
	CHECKPOINT(103, "Checkpoint"),
	
	// 2xx Success
	
	OK(200, "OK"),
	CREATED(201, "Created"),
	ACCEPTED(202, "Accepted"),
	NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
	NO_CONTENT(204, "No Content"),
	RESET_CONTENT(205, "Reset Content"),
	PARTIAL_CONTENT(206, "Partial Content"),
	MULTI_STATUS(207, "Multi-Status"),
	ALREADY_REPORTED(208, "Already Reported"),
	
	// 3xx Redirection
	
	MULTIPLE_CHOICES(300, "Multiple Choices"),
	MOVED_PERMANENTLY(301, "Moved Permanently"),
	FOUND(302, "Found"),
	SEE_OTHER(303, "See Other"),
	NOT_MODIFIED(304, "Not Modified"),
	TEMPORARY_REDIRECT(307, "Temporary Redirect"),
	PERMANENT_REDIRECT(308, "Permanent Redirect"),
	
	// --- 4xx Client Error ---
	
	BAD_REQUEST(400, "Bad Request"),
	UNAUTHORIZED(401, "Unauthorized"),
	PAYMENT_REQUIRED(402, "Payment Required"),
	FORBIDDEN(403, "Forbidden"),
	NOT_FOUND(404, "Not Found"),
	METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
	NOT_ACCEPTABLE(406, "Not Acceptable"),
	PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
	REQUEST_TIMEOUT(408, "Request Timeout"),
	CONFLICT(409, "Conflict"),
	GONE(410, "Gone"),
	LENGTH_REQUIRED(411, "Length Required"),
	PRECONDITION_FAILED(412, "Precondition Failed"),
	PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
	URI_TOO_LONG(414, "URI Too Long"),
	UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
	REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
	EXPECTATION_FAILED(417, "Expectation Failed"),
	I_AM_A_TEAPOT(418, "I'm a teapot"),
	UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
	LOCKED(423, "Locked"),
	FAILED_DEPENDENCY(424, "Failed Dependency"),
	UPGRADE_REQUIRED(426, "Upgrade Required"),
	PRECONDITION_REQUIRED(428, "Precondition Required"),
	TOO_MANY_REQUESTS(429, "Too Many Requests"),
	REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
	UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),
	
	// --- 5xx Server Error ---
	
	INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
	NOT_IMPLEMENTED(501, "Not Implemented"),
	BAD_GATEWAY(502, "Bad Gateway"),
	SERVICE_UNAVAILABLE(503, "Service Unavailable"),
	GATEWAY_TIMEOUT(504, "Gateway Timeout"),
	HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),
	VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
	INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
	LOOP_DETECTED(508, "Loop Detected"),
	BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),
	NOT_EXTENDED(510, "Not Extended"),
	NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");
	
	private static final Map<String, HttpStatus> informational = new HashMap<>();
	private static final Map<String, HttpStatus> success = new HashMap<>();
	private static final Map<String, HttpStatus> redirection = new HashMap<>();
	private static final Map<String, HttpStatus> client_error = new HashMap<>();
	private static final Map<String, HttpStatus> server_error = new HashMap<>();
	
	private static final int denominator = 100;
	
	static {
		for (HttpStatus httpStatus : values()) {
			if (httpStatus.value() / denominator == 1) {
				informational.put(httpStatus.name(), httpStatus);
			}
			if (httpStatus.value() / denominator == 2) {
				success.put(httpStatus.name(), httpStatus);
			}
			if (httpStatus.value() / denominator == 3) {
				redirection.put(httpStatus.name(), httpStatus);
			}
			if (httpStatus.value() / denominator == 4) {
				client_error.put(httpStatus.name(), httpStatus);
			}
			if (httpStatus.value() / denominator == 5) {
				server_error.put(httpStatus.name(), httpStatus);
			}
		}
	}
	
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
	
	public HttpStatus resolveInformational(String method) {
		return (method != null ? informational.get(method) : null);
	}
	
	public HttpStatus resolveSuccess(String method) {
		return (method != null ? success.get(method) : null);
	}
	
	public boolean isSuccess(String method) {
		return resolveSuccess(method) != null;
	}
	
	public HttpStatus resolveRedirection(String method) {
		return (method != null ? redirection.get(method) : null);
	}
	
	public boolean isRediraction(String method) {
		return resolveRedirection(method) != null;
	}
	
	public HttpStatus resolveClientError(String method) {
		return (method != null ? client_error.get(method) : null);
	}
	
	public HttpStatus resolveServerError(String method) {
		return (method != null ? server_error.get(method) : null);
	}
}