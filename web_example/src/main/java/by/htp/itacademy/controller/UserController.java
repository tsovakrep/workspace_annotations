package by.htp.itacademy.controller;

import javax.servlet.http.HttpSession;

import framework.webcore.annotation.controller.Controller;
import framework.webcore.annotation.controller.Mapping;
import framework.webcore.annotation.controller.method.GetMapping;

@Controller
@Mapping("user")
public class UserController {
	
	@GetMapping
	public void load(HttpSession session) {
		System.out.println("was called GET method");
		System.out.println(session.getAttribute("language"));
	}
}
