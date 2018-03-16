package by.htp.itacademy.controller;

import javax.servlet.http.HttpSession;

import framework.util.FrameworkConstant;
import framework.webcore.annotation.controller.Controller;
import framework.webcore.annotation.controller.Mapping;
import framework.webcore.annotation.controller.method.DeleteMapping;
import framework.webcore.annotation.controller.method.GetMapping;
import framework.webcore.annotation.controller.method.PostMapping;
import framework.webcore.annotation.controller.parameter.PathVariable;
import framework.webcore.annotation.controller.parameter.ReqBody;
import framework.webcore.annotation.controller.parameter.ReqParam;
import framework.webcore.annotation.initialization.AutoInit;
import framework.webcore.bean.View;

@Controller
@Mapping("/")
public class DefaultController extends AbstractLanguageController {

	@GetMapping
	public void load(HttpSession session) {
		System.out.println("was called GET method");
		loadLanguage(session);
		System.out.println(session.getAttribute("language"));
	}

	@GetMapping("callMethod/ts")
	public View fsadf(HttpSession session) {
		System.out.println("was called GET method ygy");
		return new View("template/index3.jsp");
	}
	
	@GetMapping("callMethod/pl")
	public View fasadf(HttpSession session) {
		System.out.println("was called GET method");
		return new View(FrameworkConstant.PATH_PAGES + "template/index3.jsp");
	}
	
	@PostMapping("index/show")
	public void fsaddf(HttpSession session) {
		System.out.println("was called  index/show  method");
	}

	@PostMapping
	public View dffasdfw() {
		System.out.println("was called POST method");
		return new View("template/index3.jsp");
	}

	@DeleteMapping
	public void dfffasdf() {
		System.out.println("was called DELETE method");
	}
	
	@PostMapping("callMethod")
	public void fsafdf(HttpSession session) {
		System.out.println("was called POST method");
	}

	@PostMapping("welcome/{name}")
	public View loadHomePage(
			@ReqParam(value = "userName", defaultValue = "Tsovak1") String value,
			@ReqParam(value = "userName1", defaultValue = "Tsovak2") String value2, 
			@PathVariable("name") String name,
			@ReqBody User user, HttpSession session) {

		loadLanguage(session);
		System.out.println(value);
		System.out.println(value2);
		System.out.println(name);
		System.out.println(user);
		return new View("template/index3.jsp");
	}

	private void loadLanguage(HttpSession session) {
		Object lang = session.getAttribute("language");
		if (lang == null) {
			session.setAttribute("language", "en");
		}
	}
}
