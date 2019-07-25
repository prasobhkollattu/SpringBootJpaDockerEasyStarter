package com.opensourzesupport.bootapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends BootAppServiceException {
	private static final long serialVersionUID = 8481507757899949243L;

	public ForbiddenException(String msg) {
		super(msg, "Forbidden", 403);

	}
}
