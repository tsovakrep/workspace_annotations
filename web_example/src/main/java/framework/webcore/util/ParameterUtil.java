package framework.webcore.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import framework.util.ObjectUtils;
import framework.util.chaincasttype.FacadeCast;
import framework.webcore.annotation.controller.parameter.PathVariable;
import framework.webcore.annotation.controller.parameter.ReqBody;
import framework.webcore.annotation.controller.parameter.ReqParam;
import framework.webcore.bean.Handler;
import framework.webcore.bean.Params;

/**
 * @author Tsovak Palakian
 *
 */
public class ParameterUtil {

	public static List<Object> createPathParamList(HttpServletRequest request, Method actionMethod, Handler handler) {
		List<Object> paramList = new ArrayList<Object>();

		Params paramsHelper = new Params(actionMethod);
		List<Parameter> parameters = paramsHelper.getParameters();

		int ordinal = 1;

		for (int j = 0; j < parameters.size(); j++) {

			reqParamAnnotation(paramsHelper, parameters.get(j), request, paramList);
			pathVariableAnnotation(paramsHelper, ordinal, handler, parameters.get(j), paramList);
			reqBodyAnnotation(paramsHelper, parameters.get(j), request, paramList);
			httpSession(parameters.get(j), request, paramList);
		}

		return paramList;
	}

	private static void reqParamAnnotation(Params paramsHelper, Parameter parameter, HttpServletRequest request,
			List<Object> paramList) {
		if (paramsHelper.containAnnotation(parameter, ReqParam.class)) {
			if (parameter.isAnnotationPresent(ReqParam.class)) {
				Annotation annot = getAnnotation(paramsHelper, parameter, ReqParam.class);
				if (annot != null) {
					ReqParam reqParam = (ReqParam) annot;
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
	}

	private static void pathVariableAnnotation(Params paramsHelper, int ordinal, Handler handler,
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

	private static void reqBodyAnnotation(Params paramsHelper, Parameter parameter, HttpServletRequest request,
			List<Object> paramList) {
		
		if (paramsHelper.containAnnotation(parameter, ReqBody.class)) {
			if (parameter.isAnnotationPresent(ReqBody.class)) {
				String jsonString = JSONUtil.getJsonString(request);
				Object object = JSONUtil.fromJSON(jsonString, parameter.getType());
				Map<String, String> objectRegex = ObjectValidatorUtil.mapRegex(object);
				if (ObjectUtils.isNotNullObject(object) && ObjectUtils.isNotEmptyMap(objectRegex)) {
					try {
						ObjectValidatorUtil.validator(object, objectRegex);
					} catch (Exception e) {
						
					}
				}
				paramList.add(object);
			}
		}
	}

	private static void httpSession(Parameter parameter, HttpServletRequest request, List<Object> paramList) {
		if (HttpSession.class.getName().equals(parameter.getType().getName())) {
			paramList.add(request.getSession());
		}
	}

	private static Annotation getAnnotation(Params paramsHelper, Parameter parameter,
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
