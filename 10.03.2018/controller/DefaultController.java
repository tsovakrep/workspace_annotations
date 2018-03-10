package by.htp.itacademy.controller;

import javax.servlet.http.HttpSession;

import annotationapi.annotation.Controller;
import annotationapi.annotation.DeleteMapping;
import annotationapi.annotation.GetMapping;
import annotationapi.annotation.Mapping;
import annotationapi.annotation.PathVariable;
import annotationapi.annotation.PostMapping;
import annotationapi.annotation.PutMapping;
import annotationapi.annotation.ReqBody;
import annotationapi.annotation.ReqParam;
import annotationapi.util.HttpStatus;
import annotationapi.util.ResponseEntity;
import annotationapi.util.User;

@Controller
@Mapping("/")
public class DefaultController {

	@GetMapping
	public ResponseEntity<?> load(HttpSession session) {
		System.out.println("was called GET method");
		loadLanguage(session);
		System.out.println(session.getAttribute("language"));
		return new ResponseEntity<>("WEB-INF/pages/login.jsp", HttpStatus.OK);
	}

	@GetMapping("callMethod/ts")
	public ResponseEntity<?> fsadf(HttpSession session) {
		System.out.println("was called GET method");
		return new ResponseEntity<>("WEB-INF/pages/index.jsp", HttpStatus.OK);
	}
	
	@PostMapping("index/show")
	public ResponseEntity<?> fsaddf(HttpSession session) {
		System.out.println("was called  index/show  method");
		return new ResponseEntity<>("WEB-INF/pages/template/index3.jsp", HttpStatus.OK);
	}

	@PutMapping
	public void dffasdfw() {
		System.out.println("was called PUT method");
	}

	@DeleteMapping
	public void dfffasdf() {
		System.out.println("was called DELETE method");
	}
	
	@PostMapping("callMethod")
	public ResponseEntity<?> fsafdf(HttpSession session) {
		System.out.println("was called POST method");
		return new ResponseEntity<>("WEB-INF/pages/index.jsp", HttpStatus.OK);
	}

	@PostMapping("welcome/{user}")
	public ResponseEntity<?> loadHomePage(@ReqParam(value = "userName", defaultValue = "Tsovak1") String value,
			@ReqParam(value = "userName1", defaultValue = "Tsovak2") String value2, @PathVariable("name") String name,
			@ReqBody User user, HttpSession session) {

		// loadLanguage(session);
		// System.out.println(value);
		// System.out.println(value2);
		// System.out.println(name);
		// System.out.println(user);

		return new ResponseEntity<>("WEB-INF/pages/index.jsp", HttpStatus.OK);
	}

	private void loadLanguage(HttpSession session) {
		Object lang = session.getAttribute("language");
		if (lang == null) {
			session.setAttribute("language", "en");
		}
	}
}
