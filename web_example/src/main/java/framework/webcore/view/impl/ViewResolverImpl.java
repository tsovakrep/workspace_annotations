package framework.webcore.view.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.util.FrameworkConstant;
import framework.util.ObjectUtils;
import framework.webcore.bean.Result;
import framework.webcore.bean.View;
import framework.webcore.util.JSONUtil;
import framework.webcore.util.WebUtil;
import framework.webcore.view.ViewResolver;

public class ViewResolverImpl implements ViewResolver {

	@Override
	public void resolveView(HttpServletRequest request, HttpServletResponse response, Object invokResult) {
		if (invokResult != null) {
            if (invokResult.getClass().getTypeName().equals(View.class.getName())) {
                View view = (View) invokResult;
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
                Result result = (Result) invokResult;
                JSONUtil.writeJSON(request, response, JSONUtil.toJSON(result));
            }
        }
	}
}
