package com.example.pms.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler {

	private  ErrorStructure<Object> structure;

	public ApplicationHandler(ErrorStructure<Object> structure) {
		super();
		this.structure = structure;
	}


	@ExceptionHandler(com.example.pms.exception.ProductNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> ProductNotFoundByIdException(
			com.example.pms.exception.ProductNotFoundByIdException ex) {
		ErrorStructure<String> es = new ErrorStructure<>();
		es.setErrorStatuscode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Product does not exist..");
		es.setErrorData(ex.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub

		// Iterating using List
	//	List<ObjectError> errors = ex.getAllErrors();
		//		
		//		List<String> messages = new ArrayList();
		//		
		//		errors.forEach(error -> {
		//			String message = error.getDefaultMessage();
		//			messages.add(message);
		//		
		
		// Iterating Using Map
		Map<String , String> messages = new HashMap<>();

//		errors.forEach(error -> {
//			FieldError fieldError = (FieldError) error;
//			messages.put(fieldError.getField(), fieldError.getDefaultMessage());
//			
//			messages.put(((FieldError) error).getField(), error.getDefaultMessage());
//		});
//		
		ex.getAllErrors().forEach(error -> {
			messages.put(((FieldError) error).getField(), error.getDefaultMessage());
		});
		

return ResponseEntity.badRequest().body(
		structure.setErrorStatuscode(HttpStatus.BAD_REQUEST.value())
		.setErrorMessage("Invalid Input").setErrorData(messages));
}

}
