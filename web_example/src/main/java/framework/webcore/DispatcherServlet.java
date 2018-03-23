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
}
