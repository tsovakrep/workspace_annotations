package com.logicbig.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/testT")
public class HandlerServletE extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("----- Get request for /testT ---------");
        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        writer.write("<h4>Test T page</h4>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("----- Post request for /testT ---------");
        resp.setContentType("text/html");
        
        PrintWriter writer = resp.getWriter();
        writer.write("<h4>Test T page</h4>");
        writer.write("<p>Result of post</p>");
        writer.write(req.getParameter("name")+"");
    }
}
