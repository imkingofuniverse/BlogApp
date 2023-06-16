package com.blogapp.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(UserNotFoundException exception,
	                                                                        WebRequest webRequest){
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
	                webRequest.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	    }
	 
	 
	 
	 @ExceptionHandler(PostNotFoundException.class)
	    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(PostNotFoundException exception,
	                                                                        WebRequest webRequest){
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
	                webRequest.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	    }
	 
	 @ExceptionHandler(CommentNotFoundException.class)
	    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(CommentNotFoundException exception,
	                                                                        WebRequest webRequest){
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
	                webRequest.getDescription(false));
	        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	    }
	 
}