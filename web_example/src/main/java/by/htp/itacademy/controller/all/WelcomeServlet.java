package by.htp.itacademy.controller.all;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class WelcomeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("GET WelcomeServlet -- " + request + " : " + response);
//		//System.out.println("welcomservlet");
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		
//		String n = request.getParameter("userName");
//		out.print("Welcome " + n);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST WelcomeServlet -- " + request + " : " + response);
	}
	
}
