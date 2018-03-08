package framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataContext {

    private static volatile DataContext instance = null;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private DataContext() {}

    public static DataContext getInstance(HttpServletRequest request, HttpServletResponse response) {
        if (instance == null) {
            synchronized (DataContext.class) {
                if (instance == null) {
                    instance = new DataContext();
                }
            }
        }
        return instance;
    }
}

