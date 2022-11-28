package com.EMS.api.exception;

public class EmployeeNotFoundException  extends RuntimeException
{
	public EmployeeNotFoundException(String msg) 
	{
		super(msg);
	}

}
