package framework.mvc.impl;

import org.apache.commons.collections.MapUtils;
import framework.FrameworkConstant;
import framework.mvc.ViewResolver;
import framework.mvc.bean.Result;
import framework.mvc.bean.View;
import framework.mvc.util.JSONUtil;
import framework.mvc.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DefaultViewResolver implements ViewResolver {
    @Override
    public void resolveView(HttpServletRequest request, HttpServletResponse response, Object invorkResult) {
        if (invorkResult != null) {
            if (invorkResult instanceof View) {
                View view = (View) invorkResult;
                String viewPath = view.getPath();
                if (view.isRedirect()) {
                    WebUtil.sendRedirect(request, response, viewPath);
                } else {
                    String forwardPath = FrameworkConstant.JSP_PATH + viewPath;
                    Map<String, Object> viewData = view.getData();
                    if (MapUtils.isNotEmpty(viewData)) {
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
