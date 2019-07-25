package com.opensourzesupport.bootapp.exceptions.handlers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.opensourzesupport.bootapp.exceptions.BootAppServiceException;
import com.opensourzesupport.bootapp.exceptions.models.ExceptionResponse;

/**
 * 
 * @author prasobh kollattu Centralize handler for all exceptions
 */
@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(BootAppServiceException.class)
	public ExceptionResponse handleBootAppServiceException(final BootAppServiceException exception,
			final HttpServletRequest request, final HttpServletResponse res) {

		ExceptionResponse error = exception.getError();
		error.setPath(request.getRequestURI());
		res.setStatus(exception.getErrorCode());
		return error;
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleGeneralException(final Throwable exception, final HttpServletRequest request,
			final HttpServletResponse res) {

		ExceptionResponse error = new ExceptionResponse();
		error.setPath(request.getRequestURI());
		error.setMessage("Please try after sometime");
		error.setTimestamp(LocalDateTime.now().toString());
		error.setStatus(500);
		error.setError("Internal Server Error");
		return error;
	}
}
