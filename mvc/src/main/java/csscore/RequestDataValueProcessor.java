package framework.csscore;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface RequestDataValueProcessor {

	/**
	 * Invoked when a new form action is rendered.
	 * @param request the current request
	 * @param action the form action
	 * @param httpMethod the form HTTP method
	 * @return the action to use, possibly modified
	 */
	String processAction(HttpServletRequest request, String action, String httpMethod);

	/**
	 * Invoked when a form field value is rendered.
	 * @param request the current request
	 * @param name the form field name
	 * @param value the form field value
	 * @param type the form field type ("text", "hidden", etc.)
	 * @return the form field value to use, possibly modified
	 */
	String processFormFieldValue(HttpServletRequest request, String name, String value, String type);

	/**
	 * Invoked after all form fields have been rendered.
	 * @param request the current request
	 * @return additional hidden form fields to be added, or {@code null}
	 */
	Map<String, String> getExtraHiddenFields(HttpServletRequest request);

	/**
	 * Invoked when a URL is about to be rendered or redirected to.
	 * @param request the current request
	 * @param url the URL value
	 * @return the URL to use, possibly modified
	 */
	String processUrl(HttpServletRequest request, String url);

}

