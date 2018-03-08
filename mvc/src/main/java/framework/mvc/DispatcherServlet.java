package framework.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import framework.FrameworkConstant;
import framework.mvc.bean.Handler;
import framework.mvc.util.InstanceFactory;
import framework.mvc.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	private Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	private HandlerMapping handlerMapping = InstanceFactory.getHandlerMapping();

	private HandlerInvoker handlerInvoker = InstanceFactory.getHandlerInvoker();

	private ViewResolver viewResolver = InstanceFactory.getViewResolver();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(FrameworkConstant.ENCODING);

		String reqMethod = request.getMethod();
		String requestPath = WebUtil.getRequestUrl(request);
//		logger.debug("[smart framework: {}, {}]", requestPath, reqMethod);
		if (requestPath.equals("/")) {
			WebUtil.redirectRequest(FrameworkConstant.HOME_PAGE_PATH, request, response);
		}
		
		if (requestPath.endsWith("/")) {
			requestPath = requestPath.substring(0, requestPath.length() - 1);
		}

		Handler handler = handlerMapping.getHandler(requestPath, reqMethod);
		if (handler == null) {
			WebUtil.sendError(response, HttpServletResponse.SC_NOT_FOUND, "url���method���������������");
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
