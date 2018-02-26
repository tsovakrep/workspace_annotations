package by.htp.itacademy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import annotationapi.annotation.Autoinitialization;
import annotationapi.annotation.Controller;
import annotationapi.annotation.GetMapping;
import annotationapi.annotation.Mapping;
import annotationapi.annotation.PathVariable;
import annotationapi.annotation.PostMapping;
import annotationapi.annotation.ReqBody;
import annotationapi.annotation.ReqParam;

@Controller
@Mapping("simple")
public class Simple {
	
	@Autoinitialization
	private String str;

	@GetMapping("welcome")
	public void login(@PathVariable("name") String name, 
					  @ReqParam(value = "userName", defaultValue = "Tsovak") String value, 
					  HttpSession session) 
							  throws ServletException, IOException {
		
//		System.out.println("GET -- Simple -- " + request + " : " + response);
//
//		String url = request.getRequestURI();
//		System.out.println("url: " + url);
//
//		RequestDispatcher rd = request.getRequestDispatcher("/welcome");
//		rd.forward(request, response);

	}

	@PostMapping("welcome/{user}")
	protected void logout(@ReqBody String body, HttpSession session)
			throws ServletException, IOException {
//		System.out.println("POST -- Simple -- " + request + " : " + response);
//		RequestDispatcher rd = request.getRequestDispatcher("/welcome");
//		rd.forward(request, response);
	}
}
