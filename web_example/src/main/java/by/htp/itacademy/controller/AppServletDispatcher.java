package by.htp.itacademy.controller;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.codehaus.jackson.map.ObjectMapper;

import annotationapi.util.*;

@SuppressWarnings("serial")
public class AppServletDispatcher extends HttpServlet {
	
	private static final MethodDispatcher METHOD_DISPATCHER = new MethodDispatcher();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			METHOD_DISPATCHER.callMethod(request, response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		try {
			METHOD_DISPATCHER.callMethod(request, response);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
