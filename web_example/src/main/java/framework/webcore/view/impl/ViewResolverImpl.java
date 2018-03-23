package framework.webcore.view.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.util.FrameworkConstant;
import framework.util.ObjectUtils;
import framework.webcore.bean.View;
import framework.webcore.http.HttpStatus;
import framework.webcore.util.WebUtil;
import framework.webcore.view.ViewResolver;

public class ViewResolverImpl implements ViewResolver {

	@Override
	public void resolveView(HttpServletRequest request, HttpServletResponse response, Object invokeResult) {
		if (ObjectUtils.isNotNullObject(invokeResult)) {
            if (ObjectUtils.isEqueals(invokeResult, View.class)) {
            	View<?> view = ObjectUtils.cast(invokeResult);
            	
            	String viewPath = view.getPath();
            	Object body = ObjectUtils.cast(view.getBody());
            	Map<String, ?> viewData = view.getData();
            	HttpStatus httpStatus = view.getHttpStatus();
        		
            	if (ObjectUtils.isNotNullObject(httpStatus)) {
        			if (httpStatus.isRediraction(httpStatus.name())) {
        				WebUtil.sendRedirectStatus(request, response, httpStatus, viewPath);
        			}
        		} else {
        			boolean isPage = false;
        			@SuppressWarnings("unchecked")
					List<String> pages = (List<String>) request.getServletContext().getAttribute("listOfPages");
            		for(String page : pages) {
            			if (page.contains(viewPath)) {
            				viewPath = page;
            				isPage = !isPage;
            			}
            		}
            		if (isPage) {
            			view.setPath(viewPath);
            		} else {
                		view.setPath(FrameworkConstant.PATH_PAGES + viewPath);
                	}
                	
            		if (ObjectUtils.isNotNullObject(body)) {
            			String bodyName = body.getClass().getSimpleName().toLowerCase();
            			request.setAttribute(bodyName, body);
            		}
            		
            		if (ObjectUtils.isNotEmptyMap(viewData)) {
            			for (Map.Entry<String, ?> entry : viewData.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }
            		}
            		WebUtil.forwardRequest(request, response, view);
    			}
            }
        }
	}
}
