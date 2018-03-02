package by.htp.itacademy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotationapi.util.AnnotationFinder;
import annotationapi.util.HttpMethod;
import annotationapi.util.MethodContainer;


@SuppressWarnings("serial")
public class AppServletDispatcher extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ServletContext sc = request.getServletContext();
//		AnnotationFinder af = (AnnotationFinder) sc.getAttribute("annotationfinder");
//		Map<String, MethodContainer> methodContainer = af.getMethodContainer();
//		System.out.println(methodContainer);
//		MethodContainer mc = methodContainer.get(request.getRequestURI());
//		System.out.println(mc.getUrl() + " : " + mc.getMethod().getName());
//		for (Object ob : mc.getMethodParameterTypes()) {
//			System.out.println(ob);
//		}
//		for (Object ob : mc.getParameterTypes()) {
//			System.out.println(ob);
//		}
//		Map<String, Annotation> map = mc.mapAnnotationForMethodParameters();
//		System.out.println(map);
		
		System.out.println("request.getRequestURI(): " + request.getRequestURI());
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
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
		AnnotationFinder af = (AnnotationFinder) sc.getAttribute("annotationfinder");
		Map<String, MethodContainer> methodContainer = af.getMethodContainer();
		System.out.println(methodContainer);
		MethodContainer mc = methodContainer.get(request.getRequestURI());
		System.out.println("mc: " + mc);
//		System.out.println(mc.getUrl() + " : " + mc.getMethod().getName());
		
		System.out.println("request.getRequestURI(): " + request.getRequestURI());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n = request.getParameter("userName");
		out.print("Welcome " + n);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
