package com.EMS.api.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.apache.catalina.connector.Response;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.EMS.api.exception.DepartmentNotFountException;
import com.EMS.api.exception.EmployeeAlreadyExistsException;
import com.EMS.api.exception.EmployeeNotFoundException;
import com.EMS.api.module.Employee;
import com.EMS.api.service.EmployeeService;

import antlr.debug.NewLineEvent;

@RestController
@RequestMapping("employee/")
public class EmployeeController 
{

	
	@Autowired
	private EmployeeService service;
	
	@Autowired
	private SessionFactory sessionFactory;

	
	@PostMapping("save_employee")
	public ResponseEntity<String> saveEmployee(@Valid @RequestBody Employee employee) 
{
		boolean isAdded = service.saveEmployee(employee);

		if (isAdded) 
		{
			return new ResponseEntity<String>("Employee is Added...", HttpStatus.ACCEPTED);
		
		} else 
		{
			throw new EmployeeAlreadyExistsException("Employee Already Exists with Id >> " + employee.getEmployeeId());
		}

	}

	
	@PostMapping("upload_sheet")
	public ResponseEntity<String> uploadSheet(@RequestParam MultipartFile file, HttpSession httpSession) 
	{
		
		String uploadSheet = service.uploadSheet(file, httpSession);

		return new ResponseEntity<String>(uploadSheet, HttpStatus.ACCEPTED);
	}

	
	@DeleteMapping("delete_employee_by_id{employeeId}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int employeeId) 
	{
		
		boolean isDeleted = service.deleteEmployeeById(employeeId);

		if (isDeleted) 
		{
			return new ResponseEntity<String>("Employee Deleted Successfully...", HttpStatus.ACCEPTED);
		}
		else 
		{
			throw new EmployeeNotFoundException("Employee not Found With Id >> " + employeeId);
		}

	}

	
	@GetMapping("get_allemployees")
	public ResponseEntity<List<Employee>> getAllEmployees() 
	{
		List<Employee> list = service.getAllEmployees();

		if (list != null) 
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);

		} 
		else 
		{
			throw new EmployeeNotFoundException("There is no Employee in List ");
		}
	}
	
	
	@GetMapping("get_employee_by_id/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int employeeId)
	{
		Employee employee = service.getEmployeeById(employeeId);
		
		if (employee!=null) 
		{
			return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
			
		}
		else 
		{
			throw new EmployeeNotFoundException("Employee Not Fount With Id >> "+employeeId);
		}
		
	}

	
	@PutMapping("update_employee")
	public ResponseEntity<String> updateEmployee(@Valid @RequestBody Employee employee)
	{
		boolean isUpdated = service.updateEmployee(employee);
		
		if (isUpdated) 
		{
			return new ResponseEntity<String>("Employee Updated Successfully...", HttpStatus.ACCEPTED);

		}
		else
		{
			throw new EmployeeNotFoundException("Employee Not Found With Id >> "+employee.getEmployeeId());
			
		}
		
	}

	
	@GetMapping("get_employee_by_department/{employeeDepartment}")
	public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable String employeeDepartment)
	{
		List<Employee> list = service.getEmployeeByDepartment(employeeDepartment);
		
		if (list!=null) 
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
			
		}
		else 
		{
			throw new DepartmentNotFountException(employeeDepartment+" Department not found ");
		}
	}

	
	@GetMapping("get_employee_by_name/{employeeName}")
	public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String employeeName)
	{
		List<Employee> list = service.getEmployeeByName(employeeName);
		
		if (list!=null) 
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
		}
		else 
		{
			throw new EmployeeNotFoundException("Employee not fount With name >> "+employeeName);
		}
	}

	
	@GetMapping("get_all_departmentlist")
	public ResponseEntity<List<Employee>> getAllDepartmentsList()
	{
		List<Employee> list = service.getAllDepartmentsList();
		
		if (list!=null)
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
		}
		else 
		{
			throw new DepartmentNotFountException("Department list is Empty...");
		}
		
	}

	
	@GetMapping("get_all_employee_namelist")
	public ResponseEntity<List<Employee>> getAllEmployeesNameList()
	{
		List<Employee> list = service.getAllEmployeesNameList();
		
		if (list!=null)
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee Name List is Empty...");
		}
		
	}

	
	@GetMapping("get_employee_by_fristletter/{ltr}")
	public ResponseEntity<List<Employee>> getEmployeeByFirstLetter(@PathVariable String ltr)
	{
		List<Employee> list = service.getEmployeeByFirstLetter(ltr);
		
		if (list!=null) 
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
		}
		else 
		{
			throw new EmployeeNotFoundException("Employee is not found with letter>>"+ltr);
		}
	}

	
	@GetMapping("get_employee_by_ascending_id")
	public ResponseEntity<List<Employee>> getEmployeeByAscId()
	{
		List<Employee> list = service.getEmployeeByAscId();
		
		if (list!=null)
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee Is List is empty...");
		}
	}
	
	
	@GetMapping("get_employee_by_descending_id")
	public ResponseEntity<List<Employee>> getEmployeeByDescId()
	{
		List<Employee> list = service.getEmployeeByDescId();
		
		if (list!=null)
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee Is List is empty...");
		}
	}
	
	
	@GetMapping("get_employee_by_ascending_name")
	public ResponseEntity<List<Employee>> getEmployeeByAscName()
	{
		List<Employee> list = service.getEmployeeByAscName();
		
		if (list!=null)
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee Is List is empty...");
		}
	}
	
	
	@GetMapping("get_employee_by_descending_name")
	public ResponseEntity<List<Employee>> getEmployeeByDescName()
	{
		List<Employee> list = service.getEmployeeByDescName();
		
		if (list!=null)
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee Is List is empty...");
		}
	}
	
	
	@GetMapping("get_max_salary_employee")
	public ResponseEntity<List<Employee>> getMaxSalaryEmployee()
	{
		List<Employee> list = service.getMaxSalaryEmployee();
		
		if (list!=null)
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee List is empty...");
		}
		
	}
	
	
	@GetMapping("get_min_salary_employee")
	public ResponseEntity<List<Employee>> getMinSalaryEmployee()
	{
		List<Employee> list = service.getMinSalaryEmployee();
		
		if (list!=null)
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee List is empty...");
		}
		
	}
	
	
	@GetMapping("get_total_sum_of_all_employee_salary")
	public ResponseEntity<String> getTotalSumOfAllEmployeeSalary()
	{
		String sum = service.getTotalSumOfAllEmployeeSalary();
		
		if (sum!=null)
		{
			return new ResponseEntity<String>(sum, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee salary List is Empty...");
		}
		
	}


	@GetMapping("get_total_count_of_employee")
	public ResponseEntity<String> getTotalCountOfEmployees()
	{
		String msg = service.getTotalCountOfEmployees();
		
		if (msg!=null)
		{
			return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee List is Empty");
		}
		
	}
	
	
	@GetMapping("get_total_employee_in_department/{employeeDepartment}")
	public ResponseEntity<String> getTotalEmployeeCountInDepartment(@PathVariable String employeeDepartment)
	{
		String msg = service.getTotalEmployeeCountInDepartment(employeeDepartment);
		
		if (msg!=null) 
		{
			return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee list is empty...");
		}
	}
	
	
	@GetMapping("get_employee_by_state/{employeeState}")
	public ResponseEntity<List<Employee>> getEmployeeByState(@PathVariable String employeeState)
	{
		List<Employee> list = service.getEmployeeByState(employeeState);
		
		if (list!=null)
		{
			return new ResponseEntity<List<Employee>>(list, HttpStatus.ACCEPTED);

		}
		else
		{
			throw new EmployeeNotFoundException("Employee list is empty...");
		}
		
		
	}
	

	@GetMapping("get_employee_count_by_state/{employeeState}")
	public ResponseEntity<String> getEmployeeCountByState(@PathVariable String employeeState)
	{
		String msg = service.getEmployeeCountByState(employeeState);
		
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
		
	}
	

}
