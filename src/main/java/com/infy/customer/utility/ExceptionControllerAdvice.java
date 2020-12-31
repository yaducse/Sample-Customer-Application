package com.infy.customer.utility;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.customer.exception.CustomerException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {
		
		logger.error(exception.getMessage(), exception);
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setErrorMessage(environment.getProperty(CustomerConstants.GENERAL_EXCEPTION_MESSAGE.toString()));

		return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ErrorInfo> customerExceptionHandler(CustomerException customerException) {
		
		logger.error(customerException.getMessage(), customerException);
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(environment.getProperty(customerException.getMessage()));

		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorInfo> requestParameterExceptionHandler(MissingServletRequestParameterException missingServletRequestParameterException) {

		logger.error(missingServletRequestParameterException.getMessage(), missingServletRequestParameterException);
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(missingServletRequestParameterException.getMessage()+" "
				+environment.getProperty(CustomerConstants.INPUT_PARAM_MISSING.toString()));

		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> validationExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {

		logger.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);
		
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(methodArgumentNotValidException.getBindingResult()
				.getAllErrors()
				.stream()
				.map(x -> environment.getProperty(x.getDefaultMessage()))
				.collect(Collectors.joining(", ")));

		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> constraintViolationExceptionHandler(ConstraintViolationException constraintViolationException) {
		
		logger.error(constraintViolationException.getMessage(), constraintViolationException);

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(constraintViolationException.getConstraintViolations()
				.stream()
				.map(x -> environment.getProperty(x.getMessage()))
				.collect(Collectors.joining(", ")));
		
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorInfo> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException httpMessageNotReadableException) throws Exception {
		
		logger.error(httpMessageNotReadableException.getMessage(), httpMessageNotReadableException);

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(environment.getProperty(CustomerConstants.REQUEST_BODY_MISSING.toString()));
		
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	

}
