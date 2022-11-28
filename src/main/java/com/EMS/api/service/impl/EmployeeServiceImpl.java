package com.EMS.api.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.EMS.api.dao.EmployeeDao;
import com.EMS.api.module.Employee;
import com.EMS.api.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService 
{

	@Autowired
	private EmployeeDao dao;
	
	
	int totalEmployeeInSheet=0;
	
	
	@Override
	public boolean saveEmployee(Employee employee) 
	{
		boolean isAdded = dao.saveEmployee(employee);
		
		return isAdded;
	}
	
	
	public List<Employee> readExcel(String path)
	{
		Employee employee=null;
		
		List<Employee> list = new ArrayList<>();
		
		try 
		{
			FileInputStream fileInputStream= new FileInputStream(new File(path));
		
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			
			Sheet sheet = workbook.getSheetAt(0);
			
			totalEmployeeInSheet=sheet.getLastRowNum();
			
			Iterator<Row> rows = sheet.rowIterator();
			
			int cnt=0;
			
			while (rows.hasNext()) 
			{
				employee=new Employee();
			
				Row row = rows.next();
				
				if (cnt==0) 
				{
					cnt=cnt+1;
				
					continue;
				}
				
				Iterator<Cell> cells = row.cellIterator();
				
				while (cells.hasNext()) 
				{
					Cell cell = cells.next();
				
					int column = cell.getColumnIndex();
					
					switch (column) 
					{
					case 0:
						{
							employee.setEmployeeId((int) cell.getNumericCellValue());
							
							break;
						}
					case 1:
						{
							employee.setEmployeeName(cell.getStringCellValue());
							
							break;
						}	
					case 2:
						{
							employee.setEmployeeState(cell.getStringCellValue());
							
							break;
						}
					case 3:
						{
							employee.setEmployeeAddress(cell.getStringCellValue());
							
							break;
						}
					case 4:
						{
							employee.setEmployeePhoneNumber((int)cell.getNumericCellValue());
							
							break;
						}
					case 5:
						{
							employee.setEmployeeDepartment(cell.getStringCellValue());
							
							break;
						}
					case 6:
						{
							employee.setEmployeeSalary((int)cell.getNumericCellValue());
							
							break;
						}
					default :
						{
							break;
						}
					}
					
				}
				list.add(employee);
			}
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return list;
	}

	
	@Override
	public String uploadSheet(MultipartFile file, HttpSession httpSession)
	{
		int count=0;
		
		String fileName = file.getOriginalFilename();
		
		System.out.println(fileName);
		
		String path = httpSession.getServletContext().getRealPath("/uploaded");
		
		System.out.println(path);
		
		try 
		{
			byte[] bytes = file.getBytes();
		
			FileOutputStream fileOutputStream = new FileOutputStream(new File(path+File.separator+fileName));
			
			fileOutputStream.write(bytes);
			
			List<Employee> list = readExcel(path+File.separator+fileName);
			
			for (Employee employee : list) 
			{
				System.out.println(employee);
			}
			
			count = dao.excelToDatabase(list);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "Total Employee In Sheet="+totalEmployeeInSheet+" And Now Added Employee="+count;
	}

	
	@Override
	public boolean deleteEmployeeById(int employeeId) 
	{
		boolean isDeleted = dao.deleteEmployeeById(employeeId);
		
		return isDeleted;
	}

	
	@Override
	public List<Employee> getAllEmployees() 
	{
		List<Employee> list = dao.getAllEmployees();
		
		return list;
	}

	
	@Override
	public Employee getEmployeeById(int employeeId) 

	{
		Employee employee = dao.getEmployeeById(employeeId);
		
		return employee;
	}

	
	@Override
	public boolean updateEmployee(Employee employee) 
	{
		boolean isUpdated = dao.updateEmployee(employee);
		
		return isUpdated;
	}

	
	@Override
	public List<Employee> getEmployeeByDepartment(String employeeDepartment) 
	{
		List<Employee> list = dao.getEmployeeByDepartment(employeeDepartment);
		
		return list;
	}

	
	@Override
	public List<Employee> getEmployeeByName(String employeeName) 
	{
		List<Employee> list = dao.getEmployeeByName(employeeName);
		
		return list;
	}

	
	@Override
	public List<Employee> getAllDepartmentsList() 
	{
		List<Employee> list = dao.getAllDepartmentsList();
		
		return list;
	}
	
	

	@Override
	public List<Employee> getAllEmployeesNameList() 
	{
		List<Employee> list = dao.getAllEmployeesNameList();
		
		return list;
	}
	
	

	@Override
	public List<Employee> getEmployeeByFirstLetter(String ltr) 
	{
		List<Employee> list = dao.getEmployeeByFirstLetter(ltr);
		
		return list;
	}

	
	
	@Override
	public List<Employee> getEmployeeByAscId() 
	{
		List<Employee> list = dao.getEmployeeByAscId();
		
		return list;
	}


	
	
	@Override
	public List<Employee> getEmployeeByDescId() 
	{
		List<Employee> list = dao.getEmployeeByDescId();
		
		return list;
		
	}


	
	
	@Override
	public List<Employee> getEmployeeByAscName() 
	{
		List<Employee> list = dao.getEmployeeByAscName();
		
		return list;
		
	}


	
	
	@Override
	public List<Employee> getEmployeeByDescName() 
	{
		List<Employee> list = dao.getEmployeeByDescName();
		
		return list;
		
	}


	
	
	@Override
	public List<Employee> getMaxSalaryEmployee() 
	{
		List<Employee> list = dao.getMaxSalaryEmployee();
		
		return list;
	}


	@Override
	public List<Employee> getMinSalaryEmployee() 
	{
		List<Employee> list = dao.getMinSalaryEmployee();
		
		return list;
	}


	@Override
	public String getTotalSumOfAllEmployeeSalary() 
	{
		double sum = dao.getTotalSumOfAllEmployeeSalary();
		
		return "The sum of all employee salary >> "+sum;
	}


	
	
	@Override
	public String getTotalCountOfEmployees() 
	{
		long count = dao.getTotalCountOfEmployees();
		
		return "The total count of employee >> "+count;
	}


	@Override
	public String getTotalEmployeeCountInDepartment(String employeeDepartment) 
	{
		long count = dao.getTotalEmployeeCountInDepartment(employeeDepartment);
		
		return count+" Employee as a "+employeeDepartment;
	}


	@Override
	public List<Employee> getEmployeeByState(String employeeState) 
	{
		List<Employee> list = dao.getEmployeeByState(employeeState);
		
		return list;
	}


	@Override
	public String getEmployeeCountByState(String employeeState) 
	
	{
	
		long count = dao.getEmployeeCountByState(employeeState);
		
		return "The total employee from State >> "+count;
	}


	


	
	

	

	
	
}
