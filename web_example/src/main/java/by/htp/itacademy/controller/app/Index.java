package by.htp.itacademy.controller.app;

public class Index extends Controller {
	public static void index() {
		System.out.println("Index method");
	}

	public static void show(@Param(name = "name") String name, @Param(name = "age") Integer age) {
		System.out.println("Hello " + name + ", you are " + age + " years old");
	}
}
