package annotationapi.util;

import java.util.Map;

import javax.servlet.ServletContext;

public class MethodDispatcher {

	
	private void callMethod() {
//		ServletContext sc = request.getServletContext();
//		AnnotationFinder af = (AnnotationFinder) sc.getAttribute("annotationfinder");
//
//		String uri = request.getRequestURI();
//		String[] arrUri = uri.split("/");
//		for (String string : arrUri) {
//			System.out.println(string);
//		}
//
//		int index = arrUri.length - 2;
//
//		Map<String, MethodContainer> methodContainer = af.getMethodContainer();
//		for (Map.Entry<String, MethodContainer> meth : methodContainer.entrySet()) {
//			System.out.println();
//			System.out.println(meth.getKey() + " : " + meth.getValue());
//			if (meth.getKey().endsWith("}")) {
//				String[] arrMeth = meth.getKey().split("/");
//
//				if (arrUri[index].equals(arrMeth[index])) {
//					uri = meth.getKey();
//				}
//			}
//		}
//		System.out.println(arrUri[arrUri.length - 1]);
//		System.out.println("uri: " + uri);
//		System.out.println(methodContainer);
//		MethodContainer mc = methodContainer.get(uri);
//		System.out.println("mc: " + mc);
//		System.out.println(mc.getUrl() + " : " + mc.getMethod().getName());
//
//		System.out.println("request.getRequestURI(): " + request.getRequestURI());
	}
}
