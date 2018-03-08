package framework.mvc;

import framework.mvc.bean.Handler;

public interface HandlerMapping {

    Handler getHandler(String requestPath, String requestMethod);

}

