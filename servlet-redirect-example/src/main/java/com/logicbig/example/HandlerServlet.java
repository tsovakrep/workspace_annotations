package com.logicbig.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/test2")
public class HandlerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("----- Request for /test2 ---------");
		// resp.setContentType("text/html");
		// PrintWriter writer = resp.getWriter();
		// writer.write("<h4>Test 2 page</h4>");
		req.setAttribute("tsovak", "Tsovak Palakian");
		req.getRequestDispatcher("WEB-INF/myForm4.html").include(req, resp);
		// resp.sendRedirect("WEB-INF/myForm4.html");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}