package com.logicbig.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/test")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("----- Get Request for /test ---------");
        resp.setStatus(HttpServletResponse.SC_FOUND);//302
        resp.setHeader("Location", "http://localhost:8080/servlet-redirect-example/test2");
       // resp.sendRedirect("http://localhost:8080/servlet-redirect-example/test2");
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("-----Post request for /test ---------");
        System.out.println("Post param name: " + req.getParameter("name"));
        //resp.setStatus(302);
       // resp.setHeader("Location", "http://localhost:8080/servlet-redirect-example/test2");
        resp.sendRedirect("http://localhost:8080/servlet-redirect-example/test2");
    }
}