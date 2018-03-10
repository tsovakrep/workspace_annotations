package by.htp.itacademy.framework.webcore.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.itacademy.framework.util.FrameworkConstant;
import by.htp.itacademy.framework.util.ObjectUtils;
import by.htp.itacademy.framework.webcore.ViewResolver;
import by.htp.itacademy.framework.webcore.bean.Result;
import by.htp.itacademy.framework.webcore.bean.View;
import by.htp.itacademy.framework.webcore.util.JSONUtil;
import by.htp.itacademy.framework.webcore.util.WebUtil;


public class ViewResolverImpl implements ViewResolver {

	@Override
	public void resolveView(HttpServletRequest request, HttpServletResponse response, Object invorkResult) {
		if (invorkResult != null) {
            if (invorkResult.getClass().getTypeName().equals(View.class.getName())) {
                View view = (View) invorkResult;
                String viewPath = view.getPath();
                if (view.isRedirect()) {
                    WebUtil.sendRedirect(request, response, viewPath);
                } else {
                    String forwardPath = FrameworkConstant.PATH_PAGES + viewPath;
                    Map<String, Object> viewData = view.getData();
                    if (ObjectUtils.isNotEmptyMap(viewData)) {
                        for (Map.Entry<String, Object> entry : viewData.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }
                    }
                    WebUtil.forwardRequest(request, response, forwardPath);
                }
            } else {
                Result result = (Result) invorkResult;
                WebUtil.writeJSON(request, response, JSONUtil.toJSON(result));
            }
        }
	}
}
