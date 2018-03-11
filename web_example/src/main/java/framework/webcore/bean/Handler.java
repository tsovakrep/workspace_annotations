package framework.webcore.bean;

import java.lang.reflect.Method;
import java.util.regex.Matcher;

import lombok.Data;

@Data
public class Handler {
	private Class<?> actionClass;
	private Method actionMethod;
	private Matcher requestMatcher;
	
	public Handler(Class<?> actionClass, Method actionMethod) {
        this.actionClass = actionClass;
        this.actionMethod = actionMethod;
    }
}
