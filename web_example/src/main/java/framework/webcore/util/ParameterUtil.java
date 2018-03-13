package framework.webcore.util;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import framework.util.ObjectUtils;
import framework.webcore.ParamsHelper;
import framework.webcore.annotation.PathVariable;
import framework.webcore.annotation.ReqBody;
import framework.webcore.annotation.ReqParam;
import framework.webcore.bean.Handler;
import framework.webcore.util.chaincasttype.FacadeCast;

public class ParameterUtil {

	public static List<Object> createPathParamList(HttpServletRequest request, Method actionMethod, Handler handler) {
		List<Object> paramList = new ArrayList<Object>();

		ParamsHelper paramsHelper = new ParamsHelper(actionMethod);
		
		List<Parameter> parameters = paramsHelper.getParameters();
		Parameter[] c = actionMethod.getParameters();

		int ordinal = 0;

		for (int j = 0; j < parameters.size(); j++) {

			reqParamAnnotation(paramsHelper, parameters.get(j), request, paramList);
			pathVariableAnnotation(paramsHelper, ordinal, handler, paramList);
			reqBodyAnnotation(paramsHelper, parameters.get(j), request, paramList);
			httpSession(parameters.get(j), request, paramList);
		}
		
		return paramList;
	}

	private static void reqParamAnnotation(ParamsHelper paramsHelper, Parameter parameter, HttpServletRequest request,
			List<Object> paramList) {
		if (paramsHelper.isAnnotation(ReqParam.class)) {

			ReqParam reqParam = (ReqParam) paramsHelper.getParameterAnnotations().get(parameter);
			String requestParameterValue = request.getParameter(reqParam.value());
			try {
				if (requestParameterValue != null) {
					paramList.add(FacadeCast.getCastChain().getValue(parameter.getType(), requestParameterValue));
				} else {
					paramList.add(FacadeCast.getCastChain().getValue(parameter.getType(), reqParam.defaultValue()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private static void pathVariableAnnotation(ParamsHelper paramsHelper, int ordinal, Handler handler,
			List<Object> paramList) {
		if (paramsHelper.isAnnotation(PathVariable.class)) {
			String pathVariableValue = handler.getRequestMatcher().group(ordinal);
			if (ObjectUtils.isNotEmptyString(pathVariableValue)) {
				paramList.add(pathVariableValue);
				ordinal++;
			}
		}
	}

	private static void reqBodyAnnotation(ParamsHelper paramsHelper, Parameter parameter, HttpServletRequest request,
			List<Object> paramList) {
		if (paramsHelper.isAnnotation(ReqBody.class)) {
			String jsonString = JSONUtil.getJsonString(request);
			paramList.add(JSONUtil.fromJSON(jsonString, parameter.getType()));
		}
	}
	
	private static void httpSession(Parameter parameter, HttpServletRequest request, List<Object> paramList) {
		if (HttpSession.class.getName().equals(parameter.getType().getName())) {
			paramList.add(request.getSession());
		}
	}
}
