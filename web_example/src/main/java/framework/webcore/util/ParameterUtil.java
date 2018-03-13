package framework.webcore.util;

import java.lang.annotation.Annotation;
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
		System.out.println();
		
		int ordinal = 0;

		for (int j = 0; j < parameters.size(); j++) {

			reqParamAnnotation(paramsHelper, parameters.get(j), request, paramList);
			System.out.println("paramList1: " + paramList);
			pathVariableAnnotation(paramsHelper, ordinal, handler, paramList);
			System.out.println("paramList2: " + paramList);
			reqBodyAnnotation(paramsHelper, parameters.get(j), request, paramList);
			System.out.println("paramList3: " + paramList);
			httpSession(parameters.get(j), request, paramList);
			System.out.println("paramList4: " + paramList);
		}
		
		return paramList;
	}

	private static void reqParamAnnotation(ParamsHelper paramsHelper, Parameter parameter, HttpServletRequest request,
			List<Object> paramList) {
		if (paramsHelper.containAnnotation(ReqParam.class)) {
			if (parameter.isAnnotationPresent(ReqParam.class)) {
				ReqParam reqParam = (ReqParam) getAnnotation(paramsHelper, parameter, ReqParam.class);
				String requestParameterValue = request.getParameter(reqParam.value());
				
				try {
					if (ObjectUtils.isNotEmptyString(requestParameterValue)) {
						paramList.add(FacadeCast.getCastChain().getValue(parameter.getType(), requestParameterValue));
						System.out.println("paramList11: " + paramList);
					} else {
						paramList.add(FacadeCast.getCastChain().getValue(parameter.getType(), reqParam.defaultValue()));
						System.out.println("paramList11: " + paramList);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void pathVariableAnnotation(ParamsHelper paramsHelper, int ordinal, Handler handler,
			List<Object> paramList) {
		if (paramsHelper.containAnnotation(PathVariable.class)) {
			System.out.println("is2: " + true);
			String pathVariableValue = handler.getRequestMatcher().group(ordinal);
			if (ObjectUtils.isNotEmptyString(pathVariableValue)) {
				paramList.add(pathVariableValue);
				System.out.println("paramList21: " + paramList);
				ordinal++;
			}
		}
	}

	private static void reqBodyAnnotation(ParamsHelper paramsHelper, Parameter parameter, HttpServletRequest request,
			List<Object> paramList) {
		if (paramsHelper.containAnnotation(ReqBody.class)) {
			System.out.println("is3: " + true);
			String jsonString = JSONUtil.getJsonString(request);
			paramList.add(JSONUtil.fromJSON(jsonString, parameter.getType()));
			System.out.println("paramList31: " + paramList);
		}
	}
	
	private static void httpSession(Parameter parameter, HttpServletRequest request, List<Object> paramList) {
		if (HttpSession.class.getName().equals(parameter.getType().getName())) {
			System.out.println("is4: " + true);
			paramList.add(request.getSession());
			System.out.println("paramList41: " + paramList);
		}
	}
	
	private static Annotation getAnnotation(ParamsHelper paramsHelper, Parameter parameter, Class<? extends Annotation> annotClass) {
		List<Annotation> annotations = paramsHelper.getParameterAnnotations().get(parameter);
		for (Annotation annot : annotations) {
			if (annotClass.getTypeName().equals(annot.annotationType().getName())) {
				return annot;
			}
		}
		return null;
	}
}
