package framework.webcore.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import framework.util.ObjectUtils;
import framework.webcore.annotation.PathVariable;
import framework.webcore.annotation.ReqBody;
import framework.webcore.annotation.ReqParam;
import framework.webcore.bean.Handler;
import framework.webcore.helper.ParamsHelper;
import framework.webcore.util.chaincasttype.FacadeCast;

public class ParameterUtil {

	public static List<Object> createPathParamList(HttpServletRequest request, Method actionMethod, Handler handler) {
		List<Object> paramList = new ArrayList<Object>();

		ParamsHelper paramsHelper = new ParamsHelper(actionMethod);
		List<Parameter> parameters = paramsHelper.getParameters();
		System.out.println();

		int ordinal = 1;

		for (int j = 0; j < parameters.size(); j++) {

			reqParamAnnotation(paramsHelper, parameters.get(j), request, paramList);
			pathVariableAnnotation(paramsHelper, ordinal, handler, parameters.get(j), paramList);
			reqBodyAnnotation(paramsHelper, parameters.get(j), request, paramList);
			httpSession(parameters.get(j), request, paramList);
		}

		return paramList;
	}

	private static void reqParamAnnotation(ParamsHelper paramsHelper, Parameter parameter, HttpServletRequest request,
			List<Object> paramList) {
		if (paramsHelper.containAnnotation(parameter, ReqParam.class)) {
			if (parameter.isAnnotationPresent(ReqParam.class)) {
				ReqParam reqParam = (ReqParam) getAnnotation(paramsHelper, parameter, ReqParam.class);
				String requestParameterValue = request.getParameter(reqParam.value());

				try {
					if (ObjectUtils.isNotEmptyString(requestParameterValue)) {
						paramList.add(FacadeCast.getCastChain().getValue(parameter.getType(), requestParameterValue));
					} else {
						paramList.add(FacadeCast.getCastChain().getValue(parameter.getType(), reqParam.defaultValue()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void pathVariableAnnotation(ParamsHelper paramsHelper, int ordinal, Handler handler,
			Parameter parameter, List<Object> paramList) {
		if (paramsHelper.containAnnotation(parameter, PathVariable.class)) {
			if (parameter.isAnnotationPresent(PathVariable.class)) {
				String pathVariableValue = handler.getRequestMatcher().group(ordinal);
				if (ObjectUtils.isNotEmptyString(pathVariableValue)) {
					paramList.add(pathVariableValue);
					ordinal++;
				}
			}
		}
	}

	private static void reqBodyAnnotation(ParamsHelper paramsHelper, Parameter parameter, HttpServletRequest request,
			List<Object> paramList) {
		if (paramsHelper.containAnnotation(parameter, ReqBody.class)) {
			if (parameter.isAnnotationPresent(ReqBody.class)) {
				String jsonString = JSONUtil.getJsonString(request);
				paramList.add(JSONUtil.fromJSON(jsonString, parameter.getType()));
			}
		}
	}

	private static void httpSession(Parameter parameter, HttpServletRequest request, List<Object> paramList) {
		if (HttpSession.class.getName().equals(parameter.getType().getName())) {
			paramList.add(request.getSession());
		}
	}

	private static Annotation getAnnotation(ParamsHelper paramsHelper, Parameter parameter,
			Class<? extends Annotation> annotClass) {
		List<Annotation> annotations = paramsHelper.getParameterAnnotations().get(parameter);
		for (Annotation annot : annotations) {
			if (annotClass.getTypeName().equals(annot.annotationType().getName())) {
				return annot;
			}
		}
		return null;
	}
}
