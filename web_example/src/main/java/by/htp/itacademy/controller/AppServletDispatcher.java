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
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		callMethod(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		callMethod(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		callMethod(request, response);
	}

	private void callMethod(HttpServletRequest request, HttpServletResponse response) {
		MethodDispatcher methodDispatcher = new MethodDispatcher();
		try {
			methodDispatcher.callMethodDispatcher(request, response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException	
				| InstantiationException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
