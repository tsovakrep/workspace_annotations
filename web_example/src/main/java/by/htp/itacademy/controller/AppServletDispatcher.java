package by.htp.itacademy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotationapi.annotation.Controller;
import annotationapi.util.AnnotationFinder;
import annotationapi.util.exception.ClassFindException;
import chaincasttype.CastChain;
import chaincasttype.FacadeCast;

@SuppressWarnings("serial")
public class AppServletDispatcher extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		AnnotationFinder af = new AnnotationFinder();
//		try {
//			af.annotationFind(Simple.class);
//		} catch (ClassFindException e) {
//			e.printStackTrace();
//		}
//		System.out.println(af.getAnnotationConteiner());
//		
//		Controller controller = (Controller) af.getAnnotation(Simple.class, Controller.class);
//		System.out.println("controller: " + controller);
		
//		ServletContext sc = getServletContext();
//		Set<String> list = (Set<String>) sc.getAttribute("pages");
//		System.out.println(list);
//		
//		System.out.println(req.getRequestURI());
//		System.out.println(req.getRequestURL());
//
//		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
//		rd.forward(req, resp);
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("request.getRequestURI(): " + request.getRequestURI());
		System.out.println("path: " + path);
		System.out.println("request.getContextPath(): " + request.getContextPath());
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String n = request.getParameter("userName");
		out.print("Welcome " + n);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
