package by.htp.itacademy.controller.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CurrentContext implements AutoCloseable {

	private static ThreadLocal<CurrentContext> instance = new ThreadLocal<>();
	
	@SuppressWarnings("unused")
	private HttpServletRequest request;
	@SuppressWarnings("unused")
	private HttpServletResponse response;
	
	public CurrentContext(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	
	public static CurrentContext create(HttpServletRequest request, HttpServletResponse response) {
		CurrentContext context = new CurrentContext(request, response);
		instance.set(context);
		return context;
	}
	
	public static CurrentContext getCurrentInstance() {
		return instance.get();
	}
	
	@Override
	public void close() throws Exception {
		instance.remove();
	}
	
}
