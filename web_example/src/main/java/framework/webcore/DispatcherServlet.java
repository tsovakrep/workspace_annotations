package framework.webcore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.webcore.bean.Handler;
import framework.webcore.handler.HandlerInvoker;
import framework.webcore.handler.HandlerMapping;
import framework.webcore.util.InstanceFactory;
import framework.webcore.util.WebUtil;
import framework.webcore.view.ViewResolver;

import static framework.util.FrameworkConstant.*;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {

	private HandlerMapping handlerMapping = InstanceFactory.getHandlerMapping();
	private HandlerInvoker handlerInvoker = InstanceFactory.getHandlerInvoker();
	private ViewResolver viewResolver = InstanceFactory.getViewResolver();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(ENCODING);
		
		String reqMethod = request.getMethod();
		String requestPath = request.getServletPath();

		WebUtil.startHomePage(request, response, reqMethod, requestPath);

		Handler handler = handlerMapping.getHandler(requestPath, reqMethod);
		if (handler == null) {
			WebUtil.sendError(response, HttpServletResponse.SC_NOT_FOUND, "url is wrong");
			return;
		}

		ApplicationContext.init(request, response);
		try {
			Object invokeResult = handlerInvoker.invokeHandler(request, handler);
			viewResolver.resolveView(request, response, invokeResult);
		} catch (Exception e) {
		} finally {
			ApplicationContext.destory();
		}
	}
	
	private void get(HttpServletRequest request) {
		System.out.println("1 : " + request.changeSessionId());
		System.out.println("2 : " + request.getAuthType());
		System.out.println("3 : " + request.getCharacterEncoding());
		System.out.println("4 : " + request.getContentLength());
		System.out.println("5 : " + request.getContentLengthLong());
		System.out.println("6 : " + request.getContentType());
		System.out.println("7 : " + request.getContextPath());
		System.out.println("8 : " + request.getLocalAddr());
		System.out.println("9 : " + request.getLocalName());
		System.out.println("10 : " + request.getLocalPort());
		System.out.println("11 : " + request.getMethod());
		System.out.println("12 : " + request.getPathInfo());
		System.out.println("13 : " + request.getPathTranslated());
		System.out.println("14 : " + request.getProtocol());
		System.out.println("15 : " + request.getQueryString());
		System.out.println("16 : " + request.getRemoteAddr());
		System.out.println("17 : " + request.getRemoteHost());
		System.out.println("18 : " + request.getRemotePort());
		System.out.println("19 : " + request.getRemoteUser());
		System.out.println("20 : " + request.getRequestedSessionId());
		System.out.println("21 : " + request.getRequestURI());
		System.out.println("22 : " + request.getScheme());
		System.out.println("23 : " + request.getServerName());
		System.out.println("24 : " + request.getServerPort());
		System.out.println("25 : " + request.getServletPath());
		//System.out.println("26 : " + request.getAsyncContext());
		System.out.println("27 : " + request.getAttributeNames());
		System.out.println("28 : " + request.getRequestURL());
	}
}
