package by.htp.itacademy.example;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		for (;;) {
//			Class<?> clazz = null;
//			Object obj = null;
//			try {
//				Class.forName("by.htp.itacademy.example.MyActivity");
//				clazz = Class.forName("by.htp.itacademy.processor.TimeAnnotationProcessor");
//				try {
//					obj = clazz.newInstance();
//				} catch (InstantiationException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			System.out.println(obj);
//			try {
//				new BufferedReader(new InputStreamReader(System.in)).readLine();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		TimeTest t = new TimeTest();
		t.countingTime();
	}
}
