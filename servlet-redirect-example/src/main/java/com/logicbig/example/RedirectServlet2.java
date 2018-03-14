package com.logicbig.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/test3")
public class RedirectServlet2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {


        System.out.println("----- Request for /test3 ---------");
        System.out.println(req.getParameter("name"));
        resp.setStatus(307);
        resp.setHeader("Location", "http://localhost:8080/example/test4");
    }
}