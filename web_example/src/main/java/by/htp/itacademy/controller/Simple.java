package by.htp.itacademy.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import annotation.Autoinitialization;
import annotation.Controller;
import annotation.GetMapping;
import annotation.Mapping;
import annotation.PostMapping;

@Controller
@Mapping("simple")
public class Simple {
	
	@Autoinitialization
	private String str;

//	@GetMapping("welcome")
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("GET -- Simple -- " + request + " : " + response);
//		
//		ServletContext context = getServletContext();
//		for (Map.Entry<String, ? extends ServletRegistration> servlet : context.getServletRegistrations().entrySet()) {
//			if (servlet.getValue().getClassName().startsWith("by.htp")) {
//				//System.out.println(servlet.getKey() + " : " + servlet.getValue().getClassName());
//				for (String url : servlet.getValue().getMappings()) {
//					//System.out.println("url mapping" + " : " + url);
//				}
//				if ("by.htp.itacademy.controller.WelcomeServlet".equals(servlet.getValue().getClassName())) {
//					servlet.getValue().addMapping("/welcome/palakian");
//					request.setAttribute("welcomeserv", servlet.getValue());
//				}
//			}
//		}
//		
//		request.setAttribute("tsovak", 29);
//		String url = request.getRequestURI();
//		System.out.println("url: " + url);
//
//		RequestDispatcher rd = request.getRequestDispatcher("/welcome");
//		rd.forward(request, response);
//
//	}
//
//	@PostMapping("welcome/user")
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("POST -- Simple -- " + request + " : " + response);
//		RequestDispatcher rd = request.getRequestDispatcher("/welcome");
//		rd.forward(request, response);
//	}
}
