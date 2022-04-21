package com.example.web.exception;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleAnyException2(EmptyResultDataAccessException exception, WebRequest webRequest) {
        String ERROR_MESSAGE = "id not found.."; //exception.getMessage(); //"Book Name Not Found";
        logger.error(ERROR_MESSAGE);//logger.error(ERROR_MESSAGE,exception);
        ApiErrorResponse errorResponse = getErrorResponse(HttpStatus.NOT_FOUND, ERROR_MESSAGE);
        return handleExceptionInternal(exception, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND,
                webRequest);
    }
		
	@ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleAnyException1(NoSuchElementException exception, WebRequest webRequest) {
        String ERROR_MESSAGE ="ID Not Found ...!";//exception.getMessage(); //"Book Name Not Found";
        logger.error(ERROR_MESSAGE);
        ApiErrorResponse errorResponse = getErrorResponse(HttpStatus.NOT_FOUND, ERROR_MESSAGE);
        return handleExceptionInternal(exception, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND,
                webRequest);
    }
	
     @ExceptionHandler(Exception.class)
     public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest webRequest) {
         String ERROR_MESSAGE =exception.getMessage(); //"Book Name Not Found";
         logger.error(ERROR_MESSAGE);
         ApiErrorResponse errorResponse = getErrorResponse(HttpStatus.NOT_FOUND, ERROR_MESSAGE);
         return handleExceptionInternal(exception, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND,
                 webRequest);
     }
     
     private ApiErrorResponse getErrorResponse(HttpStatus status, String errorMessage) {
         if (org.springframework.util.StringUtils.isEmpty(errorMessage)) {
             errorMessage = "An unexpected error occurred";
         }
         return new ApiErrorResponse(status.value(), status.name(), errorMessage);
     }
 
}












//private static final Logger logger = LoggerFactory.getLogger(UserControllerAdvice.class);
/*
 @Override
 protected ResponseEntity<Object> handleMethodArgumentNotValid(
         MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status,
         WebRequest request) {
     String ERROR_MESSAGE = methodArgumentNotValidException.getMessage();
     try {
         ERROR_MESSAGE = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                 .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
     } catch (Exception e) {
         logger.error("Error constructing error message", e);
     }

     logger.error(ERROR_MESSAGE, methodArgumentNotValidException);
     ApiErrorResponse errorResponse = getErrorResponse(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
     return handleExceptionInternal(methodArgumentNotValidException, errorResponse, headers, HttpStatus.BAD_REQUEST,
             request);
 }*/
/*    
 @ExceptionHandler(IllegalArgumentException.class)
 public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception,
                                                              WebRequest webRequest) {
     final String ERROR_MESSAGE = exception.getMessage();
     logger.error(ERROR_MESSAGE, exception);
     ApiErrorResponse errorResponse = getErrorResponse(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
     return handleExceptionInternal(exception, errorResponse, new HttpHeaders(),
             HttpStatus.BAD_REQUEST, webRequest);
 }

*/