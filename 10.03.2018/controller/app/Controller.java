package by.htp.itacademy.controller.app;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller {
	static protected HttpServletResponse response;
	static protected HttpServletRequest request;
	static protected ServletContext context;

	public Controller() {
		request = null;
		response = null;
		context = null;
	}

	static public void setHttp(HttpServletRequest req, HttpServletResponse res, ServletContext con) {
		request = req;
		response = res;
		context = con;
	}
}
