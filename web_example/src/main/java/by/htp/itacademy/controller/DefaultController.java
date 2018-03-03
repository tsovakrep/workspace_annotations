package by.htp.itacademy.controller;

import javax.servlet.http.HttpSession;

import annotationapi.annotation.Controller;
import annotationapi.annotation.Mapping;
import annotationapi.util.ResponseEntity;

@Controller
@Mapping("/")
public class DefaultController {
	
	public ResponseEntity loadFirstPage(HttpSession session) {
		
		return null;
	}
}
