package by.htp.itacademy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AppServletDispatcher extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		
//		String n = req.getParameter("userName");
//		out.print("Welcome " + n);
//		ServletContext sc = getServletContext();
//		Set<String> list = (Set<String>) sc.getAttribute("pages");
//		System.out.println(list.size());
//		Iterator<String> it = list.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
//		
//		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/page/index3.jsp");
//		rd.forward(req, resp);
		
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		ServletContext sc = getServletContext();
//		Set<String> list = (Set<String>) sc.getAttribute("pages");
//		System.out.println(list.size());
//		Iterator<String> it = list.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String n = req.getParameter("userName");
		out.print("Welcome " + n);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
//	protected final void processRequest(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		LocaleContext previousLocaleContext = LocaleContextHolder.getLocaleContext();
//		LocaleContext localeContext = buildLocaleContext(request);
//		
//		
//		RequestAttributes previousAttributes = RequestContextHolder.getRequestAttributes();
//		ServletRequestAttributes requestAttributes = buildRequestAttributes(request, response, previousAttributes);
//
//		WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
//		asyncManager.registerCallableInterceptor(FrameworkServlet.class.getName(), new RequestBindingInterceptor());
//
//		initContextHolders(request, localeContext, requestAttributes);
//
//		try {
//			doService(request, response);
//		}
//		catch (ServletException ex) {
//			failureCause = ex;
//			throw ex;
//		}
//		catch (IOException ex) {
//			failureCause = ex;
//			throw ex;
//		}
//		catch (Throwable ex) {
//			failureCause = ex;
//			throw new NestedServletException("Request processing failed", ex);
//		}
//
//		finally {
//			resetContextHolders(request, previousLocaleContext, previousAttributes);
//			if (requestAttributes != null) {
//				requestAttributes.requestCompleted();
//			}
//
//			publishRequestHandledEvent(request, response, startTime, failureCause);
//		}
//	}
//	
//	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		Map<String, Object> attributesSnapshot = null;
//		if (WebUtils.isIncludeRequest(request)) {
//			attributesSnapshot = new HashMap<String, Object>();
//			Enumeration<?> attrNames = request.getAttributeNames();
//			while (attrNames.hasMoreElements()) {
//				String attrName = (String) attrNames.nextElement();
//				if (this.cleanupAfterInclude || attrName.startsWith(DEFAULT_STRATEGIES_PREFIX)) {
//					attributesSnapshot.put(attrName, request.getAttribute(attrName));
//				}
//			}
//		}
//
//		// Make framework objects available to handlers and view objects.
//		request.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE, getWebApplicationContext());
//		request.setAttribute(LOCALE_RESOLVER_ATTRIBUTE, this.localeResolver);
//		request.setAttribute(THEME_RESOLVER_ATTRIBUTE, this.themeResolver);
//		request.setAttribute(THEME_SOURCE_ATTRIBUTE, getThemeSource());
//
//		FlashMap inputFlashMap = this.flashMapManager.retrieveAndUpdate(request, response);
//		if (inputFlashMap != null) {
//			request.setAttribute(INPUT_FLASH_MAP_ATTRIBUTE, Collections.unmodifiableMap(inputFlashMap));
//		}
//		request.setAttribute(OUTPUT_FLASH_MAP_ATTRIBUTE, new FlashMap());
//		request.setAttribute(FLASH_MAP_MANAGER_ATTRIBUTE, this.flashMapManager);
//
//		try {
//			doDispatch(request, response);
//		}
//		finally {
//			if (!WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
//				// Restore the original attribute snapshot, in case of an include.
//				if (attributesSnapshot != null) {
//					restoreAttributesAfterInclude(request, attributesSnapshot);
//				}
//			}
//		}
//	}

}
