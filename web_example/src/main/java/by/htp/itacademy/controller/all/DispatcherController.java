package by.htp.itacademy.controller.all;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotationapi.util.AnnotationFinder;
import annotationapi.util.exception.ClassFindException;
import by.htp.itacademy.controller.Simple;


public class DispatcherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AnnotationFinder af = new AnnotationFinder();
		try {
			af.annotationFind(Simple.class);
		} catch (ClassFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/user");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
