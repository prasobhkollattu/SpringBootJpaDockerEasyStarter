package com.opensourzesupport.bootapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DataNotFoundException extends BootAppServiceException {
	private static final long serialVersionUID = 8481507757899949243L;

	public DataNotFoundException(String msg) {
		super(msg, "DataNotFound", 404);

	}
}
