package by.htp.itacademy.controller;

import javax.servlet.http.HttpSession;

import annotationapi.annotation.Controller;
import annotationapi.annotation.GetMapping;
import annotationapi.annotation.Mapping;
import annotationapi.util.ResponseEntity;

@Controller
@Mapping("/")
public class DefaultController {
	
	@GetMapping("callMethod")
	public ResponseEntity<?> loadHomePage(HttpSession session) {
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
