package framework.webcore.bean;

import java.util.Map;

import framework.webcore.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class View<T> {
	
	private String path;
	private T body;
    private Map<String, ?> data;
    private HttpStatus httpStatus;
    
    public View(String path) {
        this(path, null, null, null);
    }
    
    public View(String path, HttpStatus httpStatus) {
        this(path, null, null, httpStatus);
    }
    
    public View(T body) {
    	this(null, body, null, null);
    }
    
    public View(T body, HttpStatus httpStatus) {
    	this(null, body, null, httpStatus);
    }
    
    public View(Map<String, ?> data) {
    	this(null, null, data, null);
    }
    
    public View(Map<String, ?> data, HttpStatus httpStatus) {
    	this(null, null, data, httpStatus);
    }
    
    public View(String path, T body) {
    	this(path, body, null, null);
    }
    
    public View(String path, T body, HttpStatus httpStatus) {
    	this(path, body, null, httpStatus);
    }
    
    public View(String path, Map<String, ?> data) {
    	this(path, null, data, null);
    }
    
    public View(String path, Map<String, ?> data, HttpStatus httpStatus) {
    	this(path, null, data, httpStatus);
    }
}
