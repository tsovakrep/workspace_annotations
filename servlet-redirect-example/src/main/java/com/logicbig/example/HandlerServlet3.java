package com.logicbig.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/test6")
public class HandlerServlet3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("----- Request for /test6 ---------");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h4>Test 6 page</h4>");
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("----- Post request for /test6 ---------");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h4>Test 6 page</h4>");
        writer.write("<p>Result of post</p>");
        writer.write(req.getParameter("name")+"");
    }
}