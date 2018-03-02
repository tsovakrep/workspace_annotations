package by.htp.itacademy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotationapi.util.AnnotationFinder;
import annotationapi.util.MethodContainer;

@SuppressWarnings("serial")
public class AppServletDispatcher extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getMethod());
		System.out.println("request.getRequestURI(): " + request.getRequestURI());
//		RequestDispatcher rd = request.getRequestDispatcher("");
//		rd.forward(request, response);
		
		ServletContext sc = request.getServletContext();
		AnnotationFinder af = (AnnotationFinder) sc.getAttribute("annotationfinder");

		String uri = request.getRequestURI();
		String[] arrUri = uri.split("/");
		for (String string : arrUri) {
			System.out.println(string);
		}

		int index = arrUri.length - 2;

		Map<String, MethodContainer> methodContainer = af.getMethodContainer();
		for (Map.Entry<String, MethodContainer> meth : methodContainer.entrySet()) {
			System.out.println();
			System.out.println(meth.getKey() + " : " + meth.getValue());
			if (meth.getKey().endsWith("}")) {
				String[] arrMeth = meth.getKey().split("/");

				if (arrUri[index].equals(arrMeth[index])) {
					uri = meth.getKey();
				}
			}
		}
		System.out.println(arrUri[arrUri.length - 1]);
		System.out.println("uri: " + uri);
		System.out.println(methodContainer);
	}

	@Override
	protected void doHead(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getMethod());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String n = request.getParameter("userName");
		out.print("Welcome " + n);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
