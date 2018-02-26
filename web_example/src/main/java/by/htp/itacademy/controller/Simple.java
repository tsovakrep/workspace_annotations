package by.htp.itacademy.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.Autoinitialization;
import annotation.Controller;
import annotation.GetMapping;
import annotation.Mapping;
import annotation.PostMapping;

@Controller
@Mapping("simple")
public class Simple {
	
	@Autoinitialization
	private String str;

	@GetMapping("welcome")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpClient client = HttpClientBuilder.create().build();
//      HttpGet request1 = new HttpGet("URL needed");
//
//      try {
//          HttpResponse response1 = client.execute(request1);
//          HttpEntity entity = response1.getEntity();
//
//          // Read the contents of an entity and return it as a String.
//          String content = EntityUtils.toString(entity);
//          System.out.println(content);
//      } catch (IOException e) {
//          e.printStackTrace();
//      }
		System.out.println("GET -- Simple -- " + request + " : " + response);

		String url = request.getRequestURI();
		System.out.println("url: " + url);

		RequestDispatcher rd = request.getRequestDispatcher("/welcome");
		rd.forward(request, response);

	}

	@PostMapping("welcome/user")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("POST -- Simple -- " + request + " : " + response);
		RequestDispatcher rd = request.getRequestDispatcher("/welcome");
		rd.forward(request, response);
	}
}
