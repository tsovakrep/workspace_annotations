package framework.mvc.bean;

import java.lang.reflect.Method;
import java.util.regex.Matcher;

public class Handler {
    private Class<?> actionClass;
    private Method actionMethod;
    private Matcher requestMatcher;

    public Handler(Class<?> actionClass, Method actionMethod) {
        this.actionClass = actionClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getActionClass() {
        return actionClass;
    }

    public void setActionClass(Class<?> actionClass) {
        this.actionClass = actionClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(Method actionMethod) {
        this.actionMethod = actionMethod;
    }

    public Matcher getRequestMatcher() {
        return requestMatcher;
    }
    public void setRequestMatcher(Matcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }
}
