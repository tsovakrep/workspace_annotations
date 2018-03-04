package by.htp.itacademy.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import annotationapi.annotation.Controller;
import annotationapi.annotation.GetMapping;
import annotationapi.annotation.Mapping;
import annotationapi.annotation.PathVariable;
import annotationapi.annotation.ReqBody;
import annotationapi.annotation.ReqParam;
import annotationapi.util.ResponseEntity;

@Controller
@Mapping("/")
public class DefaultController {
	
	@GetMapping("callMethod")
	public ResponseEntity<?> loadHomePage(
			@ReqParam(value = "userName", defaultValue = "Tsovak") Void value, 
			@ReqParam(value = "userName", defaultValue = "Tsovak") Date value2,
			@PathVariable("name") String name, 
			@ReqBody Integer body,
			HttpSession session) {
		
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
