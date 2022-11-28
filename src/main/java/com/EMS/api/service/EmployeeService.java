package com.EMS.api.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.EMS.api.module.Employee;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;

public interface EmployeeService {

	
	public boolean saveEmployee(Employee employee);
	
	
	public String uploadSheet(MultipartFile file,HttpSession httpSession);
	
	
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
	
	
	public String getTotalSumOfAllEmployeeSalary();
	
	
	public String getTotalCountOfEmployees();
	
	
	public String getTotalEmployeeCountInDepartment(String employeeDepartment);
	
	
	public List<Employee> getEmployeeByState(String employeeState);
	
	
	public String getEmployeeCountByState(String employeeState);
	
	
}
