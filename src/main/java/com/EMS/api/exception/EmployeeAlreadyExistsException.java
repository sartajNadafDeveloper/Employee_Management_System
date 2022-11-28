package com.EMS.api.exception;

public class EmployeeAlreadyExistsException  extends RuntimeException
{
	
	public EmployeeAlreadyExistsException(String msg) 
	{
		super(msg);
	}
	
}
