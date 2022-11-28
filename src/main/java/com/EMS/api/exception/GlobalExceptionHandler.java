package com.EMS.api.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javassist.expr.NewArray;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<String> employeeAlreadyExistsException(EmployeeAlreadyExistsException error)
	{
		String message = error.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> employeeNotFoundException(EmployeeNotFoundException error)
	{
		String message = error.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.OK); 
	}
	
	@ExceptionHandler(DepartmentNotFountException.class)
	public ResponseEntity<String> departmentNotFountException(DepartmentNotFountException error) 
	{
		String message = error.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object> methodArgumentNotValidException(MethodArgumentNotValidException me)
	{
		HashMap<String, Object> map = new HashMap<>();
		map.put("Time", new Date());
		me.getBindingResult().getFieldErrors().forEach(error->{ map.put(error.getField(), error.getDefaultMessage());
		
		});
		return map;
	}
	
}
