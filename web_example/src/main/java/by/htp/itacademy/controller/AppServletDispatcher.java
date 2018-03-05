package by.htp.itacademy.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotationapi.util.MethodDispatcher;


@SuppressWarnings("serial")
public class AppServletDispatcher extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		callMethod(request, response);

		// System.out.println(request.getMethod());
		// System.out.println("request.getRequestURI(): " + request.getRequestURI());
		// RequestDispatcher rd = request.getRequestDispatcher("");
		// rd.forward(request, response);
	}

	@Override
	protected void doHead(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	

		callMethod(request, response);
		
		// System.out.println(request.getMethod());
		// response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		//
		// String n = request.getParameter("userName");
		// out.print("Welcome " + n);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	private void callMethod(HttpServletRequest request, HttpServletResponse response) {
		MethodDispatcher methodDispatcher = new MethodDispatcher();
		try {
			methodDispatcher.callMethod(request, response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
