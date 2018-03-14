package com.logicbig.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/test5")
public class RedirectServlet3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {


        System.out.println("----- Request for /test5 ---------");
        resp.setStatus(308);
        resp.setHeader("Location", "http://localhost:8080/example/test6");
    }

    @Override
    protected void doPost(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {


        System.out.println("----- Post request for /test5 ---------");
        System.out.println(req.getParameter("name"));
        resp.setStatus(308);
        resp.setHeader("Location", "http://localhost:8080/example/test6");
    }
}