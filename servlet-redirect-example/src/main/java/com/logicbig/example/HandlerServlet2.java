package com.logicbig.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/test4")
public class HandlerServlet2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("----- Request for /test4 ---------");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<link rel='stylesheet' type='text/css' href='css/test4.css' />");
//        writer.write("<html>");
//        //writer.write("<title>Employee Servlet</title>");
//        //writer.write("<head>");
//        
//        //writer.write("</head>");
//        writer.write("<body>");
//        writer.write("<h4>Test 4 page</h4>");
//        writer.write("<p>Result of post</p>");
//        writer.write(req.getParameter("name"));
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        doPost(req, resp);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}