package com.logicbig.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/testE")
public class RedirectServletE extends HttpServlet {
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("----- ServletE /testE : post method---------");
        response.setStatus(307);
        response.setHeader("Location", "http://localhost:8080/servlet-redirect-example/testT");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("----- ServletE /testE : get method---------");
		// resp.setContentType("text/html");
		// resp.sendRedirect("myForm.html");
//    	request.setAttribute("user", new User("Tsovak", 29));
    	HttpSession session = request.getSession();
    	session.setAttribute("user", new User("Tsovak Palakian", 29));
    	response.sendRedirect("http://localhost:8080/servlet-redirect-example/testT");
//    	request.getRequestDispatcher("WEB-INF/file.jsp").include(request, response);
    }
}
