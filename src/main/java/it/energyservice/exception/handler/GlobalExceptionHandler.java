package it.energyservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.energyservice.exception.EnergyServiceEntityNotFoundException;
import it.energyservice.exception.EnergyServiceException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(EnergyServiceException.class)
	protected ResponseEntity<Object> handleLibraryException(EnergyServiceException ex) {

		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		return buildResponseEntity(apiError);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EnergyServiceEntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(EnergyServiceEntityNotFoundException ex) {

		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND);

		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
