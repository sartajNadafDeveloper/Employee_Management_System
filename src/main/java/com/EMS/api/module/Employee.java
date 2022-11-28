package com.EMS.api.module;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Employee 
{

	@Id
	@Min(value = 1)
	private int employeeId;
	
	@NotNull(message = "Employee Name is required")
	private String employeeName;
	
	@NotNull(message = "Employee State is required")
	private String employeeState;
	
	@NotNull(message = "Employee Address is required")
	private String employeeAddress;
	
	@Min(value = 1)
	private long employeePhoneNumber;
	
	@NotNull(message = "Employee Department is required")
	private String employeeDepartment;
	
	@Min(1)
	private double employeeSalary;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int employeeId, String employeeName, String employeeState, String employeeAddress,
			long employeePhoneNumber, String employeeDepartment, double employeeSalary) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeState = employeeState;
		this.employeeAddress = employeeAddress;
		this.employeePhoneNumber = employeePhoneNumber;
		this.employeeDepartment = employeeDepartment;
		this.employeeSalary = employeeSalary;
	}

	public int getEmployeeId() 
	{
		return employeeId;
	}

	public void setEmployeeId(int employeeId) 
	{
		this.employeeId = employeeId;
	}

	public String getEmployeeName() 
	{
		return employeeName;
	}

	public void setEmployeeName(String employeeName) 
	{
		this.employeeName = employeeName;
	}

	public String getEmployeeState() {
		return employeeState;
	}

	public void setEmployeeState(String employeeState) 
	{
		this.employeeState = employeeState;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) 
	{
		this.employeeAddress = employeeAddress;
	}

	public long getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}

	public void setEmployeePhoneNumber(long employeePhoneNumber) 
	{
		this.employeePhoneNumber = employeePhoneNumber;
	}

	public String getEmployeeDepartment() 
	{
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) 
	{
		this.employeeDepartment = employeeDepartment;
	}

	public double getEmployeeSalary() 
	{
		return employeeSalary;
	}

	public void setEmployeeSalary(double employeeSalary)
	{
		this.employeeSalary = employeeSalary;
	}

	@Override
	public String toString() 
	{
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeState="
				+ employeeState + ", employeeAddress=" + employeeAddress + ", employeePhoneNumber="
				+ employeePhoneNumber + ", employeeDepartment=" + employeeDepartment + ", employeeSalary="
				+ employeeSalary + "]";
	}
	
	
}