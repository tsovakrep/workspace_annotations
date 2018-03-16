package framework.webcore.responseentity.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.util.ObjectUtils;
import framework.webcore.bean.ResponseEntity;
import framework.webcore.http.HttpStatus;
import framework.webcore.responseentity.ResponseEntityResolver;

public class ResponseEntityResolverImpl implements ResponseEntityResolver {

	@Override
	public void resolveResponseEntity(HttpServletRequest request, HttpServletResponse response, Object invokResult) {
		if (ObjectUtils.isNotNullObject(invokResult)) {
			if (ObjectUtils.isEqueals(invokResult, ResponseEntity.class)) {
				ResponseEntity<?> responseEntity = ObjectUtils.cast(invokResult);
				Object body = responseEntity.getBody();
				String page = responseEntity.getPath();
				HttpStatus status = responseEntity.getStatus();
				if (responseEntity.isNotRedirect()) {
					
				}
			}
		}
	}
	
	
}
