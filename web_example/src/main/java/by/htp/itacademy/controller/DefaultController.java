package by.htp.itacademy.controller;

import javax.servlet.http.HttpSession;

import annotationapi.annotation.Controller;
import annotationapi.annotation.GetMapping;
import annotationapi.annotation.Mapping;
import annotationapi.annotation.PathVariable;
import annotationapi.annotation.PostMapping;
import annotationapi.annotation.ReqBody;
import annotationapi.annotation.ReqParam;
import annotationapi.util.ResponseEntity;
import annotationapi.util.User;

@Controller
@Mapping("/")
public class DefaultController {
	
	@PostMapping("welcome/{user}")
	public ResponseEntity<?> loadHomePage(
			@ReqParam(value = "userName", defaultValue = "Tsovak") String value, 
			@ReqParam(value = "userName", defaultValue = "Tsovak") String value2,
			@PathVariable("name") String name, 
			@ReqBody User body,
			HttpSession session) {
				
		System.out.println(value);
		System.out.println(value2);
		System.out.println(name);
		System.out.println(body);
		
		loadLanguage(session);
		return new ResponseEntity<>("index4");
	}
	
	private void loadLanguage(HttpSession session) {
		Object lang = session.getAttribute("language");
		if (lang == null) {
			session.setAttribute("language", "en");
		}
	}
}
