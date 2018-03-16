package framework.webcore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.util.FrameworkConstant;
import framework.webcore.bean.Handler;
import framework.webcore.handler.HandlerInvoker;
import framework.webcore.handler.HandlerMapping;
import framework.webcore.responseentity.ResponseEntityResolver;
import framework.webcore.util.InstanceFactory;
import framework.webcore.util.WebUtil;
import framework.webcore.view.ViewResolver;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {


	private HandlerMapping handlerMapping = InstanceFactory.getHandlerMapping();

	private HandlerInvoker handlerInvoker = InstanceFactory.getHandlerInvoker();

	private ViewResolver viewResolver = InstanceFactory.getViewResolver();
	
	private ResponseEntityResolver responseEntityResolver = InstanceFactory.getResponseEntityResolver();
 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(FrameworkConstant.ENCODING);
				
		String reqMethod = request.getMethod();
		String requestPath = WebUtil.getRequestUrl(request);
		
		if (requestPath.equals("/")) {
			WebUtil.forwardRequest(request, response, FrameworkConstant.HOME_PAGE);
		}

		Handler handler = handlerMapping.getHandler(requestPath, reqMethod);
		if (handler == null) {
			WebUtil.sendError(response, HttpServletResponse.SC_NOT_FOUND, "url is wrong");
			return;
		}

		ApplicationContext.init(request, response);
		try {
			Object invorkResult = handlerInvoker.invokeHandler(request, handler);
			viewResolver.resolveView(request, response, invorkResult);
		} catch (Exception e) {
		} finally {
			ApplicationContext.destory();
		}
	}
}
