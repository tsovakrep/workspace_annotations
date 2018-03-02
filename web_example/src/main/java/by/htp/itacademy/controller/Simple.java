package by.htp.itacademy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import annotationapi.annotation.Autoinitialization;
import annotationapi.annotation.Controller;
import annotationapi.annotation.DeleteMapping;
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
	public void login(@ReqParam(value = "userName", defaultValue = "Tsovak") String value, 
					  @ReqParam(value = "userName", defaultValue = "Tsovak") String value2,
					  HttpSession session) 
							  throws ServletException, IOException {
		
//		String url = request.getRequestURI();
//		System.out.println("url: " + url);
//
//		RequestDispatcher rd = request.getRequestDispatcher("/welcome");
//		rd.forward(request, response);
		

	}

	@PostMapping("welcome/{user}")
	public void logout(@PathVariable("name") String name, 
					   @ReqBody String body, HttpSession session)
							throws ServletException, IOException {
//		System.out.println("POST -- Simple -- " + request + " : " + response);
//		RequestDispatcher rd = request.getRequestDispatcher("/welcome");
//		rd.forward(request, response);
	}
	
	@DeleteMapping
	public void detete() {
		
	}
}
