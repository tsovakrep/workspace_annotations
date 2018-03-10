package by.htp.itacademy.controller;

import javax.servlet.http.HttpSession;

import by.htp.itacademy.framework.webcore.annotation.Controller;
import by.htp.itacademy.framework.webcore.annotation.DeleteMapping;
import by.htp.itacademy.framework.webcore.annotation.GetMapping;
import by.htp.itacademy.framework.webcore.annotation.Mapping;
import by.htp.itacademy.framework.webcore.annotation.PathVariable;
import by.htp.itacademy.framework.webcore.annotation.PostMapping;
import by.htp.itacademy.framework.webcore.annotation.PutMapping;
import by.htp.itacademy.framework.webcore.annotation.ReqBody;
import by.htp.itacademy.framework.webcore.annotation.ReqParam;
import by.htp.itacademy.framework.webcore.util.HttpStatus;

@Controller
@Mapping("/")
public class DefaultController {

	@GetMapping
	public void load(HttpSession session) {
		System.out.println("was called GET method");
		loadLanguage(session);
		System.out.println(session.getAttribute("language"));
	}

	@GetMapping("callMethod/ts")
	public void fsadf(HttpSession session) {
		System.out.println("was called GET method");
	}
	
	@PostMapping("index/show")
	public void fsaddf(HttpSession session) {
		System.out.println("was called  index/show  method");
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
	public void fsafdf(HttpSession session) {
		System.out.println("was called POST method");
	}

	@PostMapping("welcome/{user}")
	public void loadHomePage(@ReqParam(value = "userName", defaultValue = "Tsovak1") String value,
			@ReqParam(value = "userName1", defaultValue = "Tsovak2") String value2, @PathVariable("name") String name,
			@ReqBody User user, HttpSession session) {

		// loadLanguage(session);
		// System.out.println(value);
		// System.out.println(value2);
		// System.out.println(name);
		// System.out.println(user);

	}

	private void loadLanguage(HttpSession session) {
		Object lang = session.getAttribute("language");
		if (lang == null) {
			session.setAttribute("language", "en");
		}
	}
}
