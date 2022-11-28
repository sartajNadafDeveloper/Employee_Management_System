package com.EMS.api.dao;

import java.util.List;

import com.EMS.api.module.Employee;


public interface EmployeeDao {

	
	public boolean saveEmployee(Employee employee);
	
	
	public int excelToDatabase(List<Employee> list);
	
	
	public boolean deleteEmployeeById(int employeeId);
	
	
	public List<Employee> getAllEmployees();
	
	
	public Employee getEmployeeById(int employeeId);
	
	
	public boolean updateEmployee(Employee employee);
	
	
	public List<Employee> getEmployeeByDepartment(String employeeDepartment);

	
	public List<Employee> getEmployeeByName(String employeeName);

	
	public List<Employee> getAllDepartmentsList();

	
	public List<Employee> getAllEmployeesNameList();
	
	
	public List<Employee> getEmployeeByFirstLetter(String ltr);
	
	
	public List<Employee> getEmployeeByAscId();

	
	public List<Employee> getEmployeeByDescId();
	
	
	public List<Employee> getEmployeeByAscName();
	

	public List<Employee> getEmployeeByDescName();
	
	
	public List<Employee> getMaxSalaryEmployee();
	
	
	public List<Employee> getMinSalaryEmployee();
	
	
	public double getTotalSumOfAllEmployeeSalary();
	
	
	public long getTotalCountOfEmployees();
	

	public long getTotalEmployeeCountInDepartment(String employeeDepartment);
	
	
	public List<Employee> getEmployeeByState(String employeeState);


	public long getEmployeeCountByState(String employeeState);

}
