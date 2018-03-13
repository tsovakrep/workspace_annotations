package framework.webcore;

import javax.servlet.ServletContext;

public class DataContext {
	private static volatile DataContext instance = null;
	
	private static ServletContext servletContext;

    private DataContext() {}

    public static DataContext getInstance(ServletContext servletContext) {
        if (instance == null) {
            synchronized (DataContext.class) {
                if (instance == null) {
                    instance = new DataContext();
                    DataContext.servletContext = servletContext;
                }
            }
        }
        return instance;
    }

	public static ServletContext getServletContext() {
		return servletContext;
	}
}
